package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;

	public List<Users> findUsers(String usersName) {

		// 空文字か判定
		if (usersName.isEmpty()) {
			return usersRepository.findAll();
		}

		return usersRepository.findUsers(usersName);
	}

	public void save(UsersForm form) {
		
		Users user = new Users();
		
		user.setUserId(form.getUserId());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setRole(form.getRole());
		
		usersRepository.save(user);
	}
	
	public Optional<Users> findByUserId(String userId){
		return usersRepository.findByUserId(userId);
	}
}
