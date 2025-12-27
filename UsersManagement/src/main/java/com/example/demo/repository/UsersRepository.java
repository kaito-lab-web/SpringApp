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

/**
 * ユーザー情報の基本CRUDおよび複雑なクエリを担当するリポジトリ
 */
public interface UsersRepository extends JpaRepository<Users, String> {

	/**
	 * JPQL（Java Persistence Query Language）による曖昧検索
	 * @Query: SQLに似た構文でエンティティを操作する独自クエリを記述
	 * @Param: メソッド引数をクエリ内のプレースホルダ（:name）に紐付ける
	 */
	@Query("SELECT u FROM Users u WHERE u.name LIKE %:name%")
	List<Users> findUsers(@Param("name") String name);

	/**
	 * user_idでよる一致検索
	 * @param userId 検索対象のユーザーID
	 * @return 見つからない可能性を場合は、Null安全なOptional型で返却
	 */
	Optional<Users> findByUserId(String userId);

	/**
	 * 最終ログイン日時の更新
	 * @Transactional: 一連の処理をトランザクションとして管理し、失敗時にロールバック可能にする
	 * @Modifying: SELECT以外の操作（INSERT, UPDATE, DELETE）であることをSpringに明示
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.lastLoginDate = :date WHERE u.userId = :userId")
	void updateLastLoginDate(@Param("userId") String userId, @Param("date") LocalDateTime date);

	/**
	 * メールアドレスによる一致検索
	 * @param mail 検索対処のメールアドレス
	 * @return 見つからない可能性を場合は、Null安全なOptional型で返却
	 */
	Optional<Users> findByMail(String mail);

}