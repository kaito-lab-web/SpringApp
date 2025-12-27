package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザー情報編集画面用のフォーム。
 * DBの項目（Entity）とは異なり、確認用パスワードなど画面独自の項目を持ちます。
 */
@Data
public class UserEditForm {

	// 現在のパスワード（変更時の本人確認用）
	private String currentPassword;

	// 新しいパスワード（入力がある場合のみ変更処理を行う）
	private String newPassword1;
	private String newPassword2; // 確認用

	@NotBlank(message = "名前は必須です!!")
	private String name;

	private String mail;
}