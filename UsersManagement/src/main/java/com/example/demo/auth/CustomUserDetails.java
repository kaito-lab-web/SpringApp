package com.example.demo.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Users;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityが提供する「UserDetails」インターフェースの実装クラス。
 * データベースから取得した Users エンティティをラップし、
 * Securityが認証・認可の判断を行える形式に変換します。
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	/** データベースから取得したユーザー情報の本体（Entity） */
	private final Users user;

	/**
	 * ユーザーに付与されている権限（ロール）を返却します。
	 * * @return 権限のコレクション
	 * Collections.singletonList: 要素が1つだけの変更不可能なリストを作成
	 * SimpleGrantedAuthority: "ROLE_USER" などの文字列をSecurityの権限形式に変換
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// エンティティに保存されている role（ROLE_USERなど）を権限として登録
		return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
	}

	/**
	 * 認証に使用するパスワードを返却します。
	 * Spring Securityはこの値と、ログイン画面で入力されたパスワードを比較します。
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * 認証に使用するユーザー名を返却します。
	 * 本システムでは「ユーザーID」を一意の識別子として使用します。
	 */
	@Override
	public String getUsername() {
		return user.getUserId();
	}

	/**
	 * 以下、アカウントの状態に関するメソッド群
	 * 全て true を返すことで、常に有効なアカウントとして扱います。
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true; // アカウントの有効期限切れ（なし）
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // アカウントのロック（なし）
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // パスワードの有効期限切れ（なし）
	}

	@Override
	public boolean isEnabled() {
		return true; // アカウント自体の有効化（有効）
	}
}