package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.PasswordResetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
	
	private final PasswordResetRepository passwordResetRepository;
	
	
	public Optional<Users> findByMail(String mail){
		return passwordResetRepository.findByMail(mail);
	}

}
