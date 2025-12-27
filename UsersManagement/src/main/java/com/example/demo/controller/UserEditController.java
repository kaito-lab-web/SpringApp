package com.example.demo.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.form.UserEditForm;
import com.example.demo.service.UsersEditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserEditController {

	private final UsersEditService usersEditService;
	private final PasswordEncoder passwordEncoder;

	/**
	 * @param principal ログイン中のユーザー情報。getName()でログインIDが取れる。
	 */
	@GetMapping("/user-edit")
	public String view(UserEditForm userEditForm, Principal principal) {
		// 現在のログインユーザー情報をDBから取得してフォームにセット（初期表示）
		Users user = usersEditService.findByUserId(principal.getName());
		userEditForm.setName(user.getName());
		userEditForm.setMail(user.getMail());
		return "user-edit";
	}

	@PostMapping("/user-edit")
	public String update(Model model, @Validated UserEditForm userEditForm, BindingResult result, Principal principal) {
		if (result.hasErrors())
			return "user-edit";

		Users user = usersEditService.findByUserId(principal.getName());
		String passwordToSave = user.getPassword(); // 変更しない場合は今の値を維持

		// パスワード変更ロジック
		if (userEditForm.getCurrentPassword() != null) {
			// passwordEncoder.matches: 平文とハッシュ値を比較する
			if (!passwordEncoder.matches(userEditForm.getCurrentPassword(), user.getPassword())) {
				result.rejectValue("currentPassword", null, "現在のパスワードが正しくありません");
				return "user-edit";
			}
			// 新パスワードをハッシュ化して保存用変数へ
			passwordToSave = passwordEncoder.encode(userEditForm.getNewPassword1());
		}
		// ...以下、Serviceへ保存依頼
		return "redirect:/user-edit?success";
	}
}