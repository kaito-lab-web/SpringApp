package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.PasswordResetRepository;

import lombok.RequiredArgsConstructor;

/**
 * パスワード再設定に関する業務ロジックを担当
 */
@Service
@RequiredArgsConstructor
public class PasswordResetService {

	private final PasswordResetRepository passwordResetRepository;

	/**
	 * メール送信先を確認するために、メールアドレスでユーザーの存在をチェックする
	 */
	public Optional<Users> findByMail(String mail) {
		return passwordResetRepository.findByMail(mail);
	}
}