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

		List<Users> users;
		
		if (usersForm.getName() == null || usersForm.getName().isBlank()) {
			users = usersService.findUsers("");
		} else {
			users = usersService.findUsers(usersForm.getName());
		}
		
		model.addAttribute("users", users);
		model.addAttribute("usersForm", usersForm);
		// Springがmodel.addAttribute("usersForm", usersForm)を自動でしてくれてる
		/**
		 * Springが自動でModelに登録するときの名前は、デフォルトで「クラス名の先頭を小文字にしたもの」
		 * Java: public String login(LoginForm abc)
		 * 変数名は abc だが、Modelには自動的に loginForm という名前で入る
		 * HTML: th:object="${loginForm}"
		 * この場合、${abc} ではなく ${loginForm} と書く必要があります。
		 */
		
		// Springが自動でしてくれたaddAttribute("usersForm", usersForm)を
		// newして手動で登録すると検索窓に検索履歴が残らない
		//model.addAttribute("usersForm", new UsersForm());

		return "users-view";
	}

}