package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * パスワード再設定画面用のフォーム。
 */
@Data
public class PasswordResetForm {

	/**
	 * @NotBlank: 未入力や空文字、スペースのみの場合にエラーにします。
	 * message: エラー時に画面に表示する文言を指定します。
	 */
	@NotBlank(message = "メールアドレスを入力してください。")
	private String mail;

}