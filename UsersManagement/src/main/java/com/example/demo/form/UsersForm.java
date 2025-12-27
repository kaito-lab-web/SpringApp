package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * ユーザー情報の基本データを保持するフォーム。
 * 強力なバリデーションルールを設定し、不正なデータがDBに入るのを防ぎます。
 */
@Data
public class UsersForm {

	/**
	 * @Size: 文字数の範囲を制限します。
	 */
	@NotBlank(message = "ユーザーIDは必須です")
	@Size(min = 1, max = 10, message = "1～10文字で入力してください")
	private String userId;

	@NotBlank(message = "パスワードは必須です!!")
	private String password;

	/** 権限。初期値として "ROLE_USER" を設定 */
	@NotBlank
	private String role = "ROLE_USER";

	@NotBlank(message = "名前は必須です!!")
	private String name;

	private String mail;
}