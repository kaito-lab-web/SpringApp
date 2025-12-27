package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersController {
	
	private final UsersService usersService;

	@GetMapping("/users-view")
	public String view(UsersForm usersForm, Model model) {
		/**
		 * Springの「Model自動登録」の仕組み：
		 * 引数に UsersForm を書くと、自動的に model.addAttribute("usersForm", usersForm) が実行される。
		 * そのため、HTML側で th:object="${usersForm}" と書けば、検索条件を保持したまま画面を表示できる。
		 */
		List<Users> users = usersService.findUsers(usersForm.getName() == null ? "" : usersForm.getName());
		model.addAttribute("users", users);
		return "users-view";
	}
}