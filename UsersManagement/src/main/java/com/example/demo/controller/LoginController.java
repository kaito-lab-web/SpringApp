package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.LoginForm;

@Controller
public class LoginController {
	
	// @ModelAttribute
	/**
	 * ユーザーが画面で「名前」を入力して送信したときにその値を自動的に
	 * UsersFormのフィールド（name）にセットしてくれる
	 * 
	 */
	@GetMapping("/login")
	public String view(@ModelAttribute LoginForm loginForm) {
		
		return "login";
	}
	
	// localhost:8090/もログイン画面に遷移
	@GetMapping("/")
	public String view() {
	    
	    return "redirect:/login";
	}
}
