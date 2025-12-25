package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PasswordResetForm {
	
	@NotBlank(message = "メールアドレスを入力してください。")
	private String mail;

}
