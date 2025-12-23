package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	// UserDetailsService : 
	// Spring Secrityが定めたユーザー情報を取得する共通ルールのインターフェース
	
	// CustomUserDetailsService :
	// DBからユーザー情報を取得してSpring Secrityが読める形にする
	
    private final UsersRepository usersRepository;
    
    /**
     *  DaoAuthenticationProviderクラスがログインの際に
     *  Spring Securityの認証フィルターからloadUserByUsernameを呼び出して
     *  ユーザー情報を取得する
     */
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        
    	// repositoryにユーザーIDが必要か尋ねる
        return usersRepository.findByUserId(userId)
               
        		/**
        		 * ユーザーIDが見つかった場合
        		 * 
        		 * getDbUserはfindByUserId(userId)で取ってきた UsersのEntity
        		 */
        		.map(getDbUser -> {
                    
        			// Spring SecurityがCustomUserDetailsクラスでしか受け取らない
                    return new CustomUserDetails(getDbUser);
                })
        		 		
        		/**
        		 * ユーザーIDが見つからなかった場合（例外を投げる）
        		 * 
        		 * Spring SecurityにあるuthenticationFailureHandlerが例外キャッチしてる
        		 * SecurityConfigで設定した.failureUrl("/login?error")に飛ばされる
        		 */
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));
    }
}