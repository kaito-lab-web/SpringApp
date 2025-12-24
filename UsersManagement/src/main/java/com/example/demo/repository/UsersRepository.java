package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

	/**
	 * JpaRepositoryを継承するとSpringAppが起動時に実装クラスを生成してくれる
	 * 
	 */

	@Query("SELECT u FROM Users u WHERE u.name LIKE %:name%")
	List<Users> findUsers(@Param("name") String name);

	/**
	 * 命名ルールによってJpaRepositoryがSQLを自動生成してくれる
	 * find : SELECT
	 * By : WHERE
	 * UserId : カラム名（Entityと揃える）
	 * SELECT * FROM users WHERE user_id = ?
	 */
	Optional<Users> findByUserId(String userId);

	@Transactional // 更新処理に必要
	@Modifying // データの変更（UPDATE/DELETE）を伴う場合に必要
	@Query("UPDATE Users u SET u.lastLoginDate = :date WHERE u.userId = :userId")
	void updateLastLoginDate(@Param("userId") String userId, @Param("date") LocalDateTime date);
	
	Optional<Users> findByMail(String mail);

}
