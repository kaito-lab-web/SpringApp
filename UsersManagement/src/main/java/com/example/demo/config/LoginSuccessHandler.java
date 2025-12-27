package com.example.demo.config;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UsersRepository;

/**
 * ログイン成功時のカスタムハンドラ。
 * SavedRequestAwareAuthenticationSuccessHandler を継承することで、
 * 「ログイン前にアクセスしようとしていたURL」を覚えたまま、成功後の処理を追加できます。
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UsersRepository userRepository;

	/**
	 * 認証が成功したときに自動的に呼び出されるメソッド
	 * @param request HTTPリクエスト情報
	 * @param response HTTPレスポンス情報（リダイレクトなどに使用）
	 * @param authentication 認証に成功したユーザーの情報（名前や権限など）が格納されている
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 最終ログイン日時を更新
		/**
		 * authentication.getName() は、CustomUserDetailsで返した getUsername()（ユーザーID）を取得します。
		 * findById は JpaRepository の標準メソッドで、主キーで検索します。
		 */
		userRepository.findById(authentication.getName()).ifPresent(user -> {
			// 受け取ったuser全データの最終ログイン日時だけ上書き
			user.setLastLoginDate(LocalDateTime.now());

			// 更新処理を実行（saveメソッドは、IDが存在すればUPDATE文を発行します）
			userRepository.save(user);
		});

		// 遷移先へ移動させる
		/**
		 * ログイン成功後、ユーザーをメニュー画面へ強制的にリダイレクトさせます。
		 */
		response.sendRedirect("/menu");
	}
}