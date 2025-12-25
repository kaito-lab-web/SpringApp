package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.PasswordResetForm;
import com.example.demo.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PasswordResetController {

	private final PasswordResetService passwordResetService;

	@GetMapping("/password-reset")
	public String getView(PasswordResetForm passwordResetForm) {
		return "password-reset";
	}

	@PostMapping("/password-reset")
	public String postView(@Valid PasswordResetForm passwordResetForm, BindingResult result, Model model) {

		// バリデーションエラーがある場合
		if (result.hasErrors()) {
			return "password-reset";
		}

		// メールアドレスの存在チェック
		var userOpt = passwordResetService.findByMail(passwordResetForm.getMail());

		// メールアドレスの重複チェック
		if (userOpt.isPresent()) {
			
			model.addAttribute("successMessage", "指定のメールアドレスに送信しました");
			
		} else {
			
			model.addAttribute("duplicationError", "入力されたメールアドレスは登録されていません");
		}

		return "password-reset";
	}
}
