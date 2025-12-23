package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UsersForm {
	
	@Size(min= 1, max = 10, message = "1～4桁を入力してください")
    private String userId;
	
    private String name;
    
    @NotBlank(message="パスワードは必須です!!")
    private String password;
}