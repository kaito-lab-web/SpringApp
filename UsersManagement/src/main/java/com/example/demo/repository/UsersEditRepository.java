package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

/**
 * ユーザー編集機能専用のリポジトリ
 */
public interface UsersEditRepository extends JpaRepository<Users, String> {

	/**
	 * user_idでよる一致検索
	 * @param userId 検索対象のユーザーID
	 * @return 戻り値が Optional ではなく Users 単体のため、存在しないIDを指定した場合は null が返却される
	 */
	Users findByUserId(String userId);

}