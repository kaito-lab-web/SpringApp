package com.example.demo.service;

import java.time.LocalDateTime;
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

	public void save(UsersForm userForm) {
		
		Users user = new Users();
		
		user.setUserId(userForm.getUserId());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setRole(userForm.getRole());
		user.setName(userForm.getName());
		user.setMail(userForm.getMail());
		user.setAddDate(LocalDateTime.now());
		user.setLastLoginDate(LocalDateTime.now());
		user.setAddountEnable(true);
		
		usersRepository.save(user);
	}
	
	public Optional<Users> findByUserId(String userId){
		return usersRepository.findByUserId(userId);
	}
	
	public Optional<Users> findByMail(String mail){
		return usersRepository.findByMail(mail);
	}
}
