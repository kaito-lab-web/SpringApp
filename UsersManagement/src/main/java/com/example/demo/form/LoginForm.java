package com.example.demo.form;

import lombok.Data;

/**
 * ログイン画面用のフォーム。
 * Spring Securityの認証プロセスに渡すためのuserIdとpasswordを保持します。
 */
@Data
public class LoginForm {
	
	private String userId;
	private String password;
	
}