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

	@GetMapping("/sign-up")
	public String getView(UsersForm usersForm) {
		return "sign-up";
	}

	@PostMapping("/sign-up")
	public String postView(@Valid UsersForm usersForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		// バリデーションエラー
		if (result.hasErrors()) {
			return "sign-up";
		}

		// ユーザーIDの重複チェック
		if (usersService.findByUserId(usersForm.getUserId()).isPresent()) {
			model.addAttribute("duplicationError", "このユーザーIDは既に使用されています");
			return "sign-up";
		}
		
		// メールアドレス重複
		// InitBinderのおかげで、未入力ならusersForm.getMail()はnullになっています
	    if (usersForm.getMail() != null && usersService.findByMail(usersForm.getMail()).isPresent()) {
	        model.addAttribute("duplicationError", "このメールアドレスは既に登録されています");
	        return "sign-up";
	    }

		// 登録
		usersService.save(usersForm);
		
		// 登録完了メッセージ
		redirectAttributes.addFlashAttribute("successMessage", "ユーザー「" + usersForm.getName() + "」さんの新規登録が完了しました！");

		// 登録画面へリダイレクト（これで成功メッセージが表示される）
		return "redirect:/sign-up";

	}
	
	/**
	 * 呼び出す必要はなく自動で適応される
	 * @InitBinder が同じクラスにあるメソッドを見つけて実行する
	 * 空文字をnullにしてくれる
	 */
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        // StringTrimmerEditor(true) が「空文字をnullにする」
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}