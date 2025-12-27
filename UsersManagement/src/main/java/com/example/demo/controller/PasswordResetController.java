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

		// 1. バリデーションエラー（@NotBlankなど）がある場合は画面に戻す
		if (result.hasErrors()) {
			return "password-reset";
		}

		// 2. サービスを使ってメールアドレスの存在チェック
		var userOpt = passwordResetService.findByMail(passwordResetForm.getMail());

		if (userOpt.isPresent()) {
			// 存在する場合：成功メッセージ
			model.addAttribute("successMessage", "指定のメールアドレスに送信しました");
		} else {
			// 存在しない場合：エラーメッセージ
			model.addAttribute("duplicationError", "入力されたメールアドレスは登録されていません");
		}

		return "password-reset";
	}
}