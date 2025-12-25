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

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// 最終ログイン日時を更新
		userRepository.findById(authentication.getName()).ifPresent(user -> {
			// 受け取ったuser全データの最終ログイン日時だけ上書き
		    user.setLastLoginDate(LocalDateTime.now());
		 // 更新
		    userRepository.save(user);
		});

		// 遷移先へ移動させる
		response.sendRedirect("/menu");
	}
}