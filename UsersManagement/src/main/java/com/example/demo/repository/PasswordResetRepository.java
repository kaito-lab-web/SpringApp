package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

/**
 * パスワードリセット機能専用のリポジトリ
 */
public interface PasswordResetRepository extends JpaRepository<Users, String> {

	/**
	 * メールアドレスでユーザーを検索する
	 * @param mail 検索対象のメールアドレス
	 * @return 見つからない可能性を場合は、Null安全なOptional型で返却
	 */
	Optional<Users> findByMail(String mail);
}