package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

/**
 * データベースのテーブルと1対1で対応するエンティティクラス。
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示します。
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成します。
 */
@Entity
@Data
public class Users {

	/**
	 * @Id: テーブルの主キー（Primary Key）であることを示します。
	 * @Column: Javaのフィールド名とDBのカラム名が異なる場合に、対応する名前を指定します。
	 */
	@Id
	@Column(name = "user_id")
	private String userId; // ログインID（主キー）

	private String password; // ハッシュ化済みのパスワード
	private String role; // ユーザー権限（ROLE_USER, ROLE_ADMINなど）
	private String name; // ユーザー名
	private String mail; // メールアドレス

	/** 作成日時 */
	@Column(name = "add_date")
	private LocalDateTime addDate;

	/** 更新日時 */
	@Column(name = "upd_date")
	private LocalDateTime updDate;

	/** * アカウントが有効かどうかを示すフラグ
	 * 命名のヒント: 
	 * フィールド名は addountEnable ですが、DB側は account_enabled という名前になっています。
	 */
	@Column(name = "account_enabled")
	private boolean addountEnable;

	/** 最後にログインした日時。LoginSuccessHandlerで更新されます。 */
	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;

	// emailaccount_enabled

	// last_login_date
}