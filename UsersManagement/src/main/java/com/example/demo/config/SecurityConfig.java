package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 設定クラスファイルを表す
@EnableWebSecurity // Spring Securityを有効
public class SecurityConfig {

	/**
	 * アクセス制限（誰が通れるか）＆ ログイン方法（どうやって入るか）を設定してる
	 */

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
	    http
	        // 1. まず全体でCSRFを無効化（これでどこでもPOSTが通るようになります）
	        .csrf(csrf -> csrf.disable())
	        
	        // 2. アクセス権限の設定
	        .authorizeHttpRequests(authz -> authz
	        		// ここは誰でも可能
	                .requestMatchers("/login", "/h2-console/**").permitAll()
	             // それ以外はログイン必須
	                .anyRequest().authenticated())
	        
	        // 3. ログインの設定
	        .formLogin(form -> form
	        		// 自作のログイン画面を使う
	                .loginPage("/login")
	             // ログイン処理をどこでするか
	                .loginProcessingUrl("/login")
	              //HTMLの <input name="userId">と紐付いてる（userIdをIDとして使う）
	                .usernameParameter("userId")
	              //HTMLの <input name="password">と紐付いてる（passwordをpasswordとして使う）
	                .passwordParameter("password")
	             // ログイン成功後のURL
	                .defaultSuccessUrl("/users-view", true)
	                .failureUrl("/login?error")
	                .permitAll())
	        
	        // 4. ログアウトの設定
	        .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout"));

	    // 5. H2コンソールの表示設定（CSRF無効化は上でやったので、これだけでOK）
	    http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

	    return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
