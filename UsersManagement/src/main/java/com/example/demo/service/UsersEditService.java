package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersEditRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報変更画面に関する業務ロジックを担当
 */
@Service
@RequiredArgsConstructor
public class UsersEditService {

	private final UsersEditRepository usersEditRepository;

	/**
	 * ログイン中のユーザー情報を編集画面に表示するために取得する
	 */
	public Users findByUserId(String userId) {
		return usersEditRepository.findByUserId(userId);
	}
}