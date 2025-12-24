package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UsersForm;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {

	private final UsersService usersService;

	@GetMapping("/sign-up")
	public String getView(UsersForm usersForm) {
		return "sign-up";
	}

	// 新規作成
	@PostMapping("/sign-up")
	public String postView(@Valid UsersForm usersForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "sign-up";
		}
		
		if (usersService.findByUserId(usersForm.getUserId()).isPresent()) {
			
			model.addAttribute("duplicationError", "このユーザーIDは既に使用されています");
			return "sign-up";
		}
		
		usersService.save(usersForm);

		return "sign-up";
	}

}
