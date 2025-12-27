package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の検索・保存・更新といった共通の業務ロジックを担当
 */
@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;

	/**
	 * ユーザー一覧を取得する（曖昧検索対応）
	 */
	public List<Users> findUsers(String usersName) {
		// 空文字か判定（検索条件がない場合は全件取得）
		if (usersName.isEmpty()) {
			return usersRepository.findAll();
		}
		return usersRepository.findUsers(usersName);
	}

	/**
	 * ユーザー情報の保存（新規・更新の両方に対応）
	 * @param userForm コントローラーで受け取った入力データ
	 */
	public void save(UsersForm userForm) {
		/**
		 * 既存ユーザーを取得（更新の場合）。いなければ空のEntityを作成（新規登録の場合）。
		 * これにより、登録日時(addDate)などの既存データを守りながら更新できる。
		 */
		Users user = usersRepository.findByUserId(userForm.getUserId()).orElse(new Users());

		// フォームの値をEntityに詰め替える
		user.setUserId(userForm.getUserId());
		user.setName(userForm.getName());
		user.setMail(userForm.getMail());

		// 【修正】Controllerでハッシュ化済みなので、そのままセットする
		// セキュリティ上、生のパスワードをここまで持ち込まない設計
		user.setPassword(userForm.getPassword());

		// 新規登録の時（まだDBにデータがない時）だけに設定したい項目
		if (user.getAddDate() == null) {
			user.setRole(userForm.getRole());
			user.setAddDate(LocalDateTime.now());
			user.setAddountEnable(true);
		}

		// 更新日時は保存のたびに最新にする
		user.setUpdDate(LocalDateTime.now());

		// DBへ反映
		usersRepository.save(user);
	}

	public Optional<Users> findByUserId(String userId) {
		return usersRepository.findByUserId(userId);
	}

	public Optional<Users> findByMail(String mail) {
		return usersRepository.findByMail(mail);
	}
}