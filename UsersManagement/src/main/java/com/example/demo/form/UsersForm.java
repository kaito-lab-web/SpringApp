package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UsersForm {
	
	@NotBlank(message = "ユーザーIDは必須です")
    @Size(min = 1, max = 10, message = "1～10文字で入力してください")
    private String userId;
	
    @NotBlank(message="パスワードは必須です!!")
    private String password;
    
    @NotBlank
    private String role = "ROLE_USER";
    
    @NotBlank(message="名前は必須です!!")
    private String name;
    
    private String mail;
}