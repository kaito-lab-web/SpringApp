package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.LoginForm;

@Controller
public class LoginController {

	/**
	 * @ModelAttribute
	 * ユーザーが画面で「名前」を入力して送信したときにその値を自動的に
	 * UsersFormのフィールド（name）にセットしてくれる
	 */
	@GetMapping("/login")
	public String view(@ModelAttribute LoginForm loginForm) {
		// return "login" は、src/main/resources/templates/login.html を指す
		return "login";
	}

	// localhost:8090/ アクセス時もログイン画面にリダイレクトさせる
	@GetMapping("/")
	public String view() {
		// redirect: をつけると、ブラウザのURL自体が書き換わる
		return "redirect:/login";
	}
}