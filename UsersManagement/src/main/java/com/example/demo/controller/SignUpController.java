package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.UsersForm;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {

	private final UsersService usersService;

	/**
	 * 新規登録画面の表示 (GET)
	 * @param usersForm フォームオブジェクト。
	 * Thymeleafの th:object="${usersForm}" と紐付けるために空のインスタンスが必要。
	 * @return 表示するHTMLのパス
	 */
	@GetMapping("/sign-up")
	public String getView(UsersForm usersForm) {
		// return "sign-up" により、src/main/resources/templates/sign-up.html を表示します
		return "sign-up";
	}

	/**
	 * 新規登録処理 (POST)
	 */
	@PostMapping("/sign-up")
	public String postView(@Valid UsersForm usersForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		// 1. バリデーションエラー（入力必須チェックなど）
		if (result.hasErrors()) {
			return "sign-up";
		}

		// 2. ID重複チェック
		if (usersService.findByUserId(usersForm.getUserId()).isPresent()) {
			model.addAttribute("duplicationError", "このユーザーIDは既に使用されています");
			return "sign-up";
		}

		// 3. 保存実行
		usersService.save(usersForm);

		// RedirectAttributes: ブラウザのURLを書き換えるリダイレクト後でも、
		// 一度だけメッセージを表示できる（フラッシュ属性）。
		redirectAttributes.addFlashAttribute("successMessage", "ユーザー「" + usersForm.getName() + "」さんの新規登録が完了しました！");

		// PRG(Post-Redirect-Get)パターン。登録後はGETリクエストへリダイレクトして二重送信を防止。
		return "redirect:/sign-up";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// ユーザーが全角スペース等を入れた場合や未入力の場合、"" ではなく null として扱う設定
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}