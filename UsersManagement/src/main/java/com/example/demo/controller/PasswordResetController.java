package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordResetController {
	
	@GetMapping("password-reset")
	public String view() {
		
		return "password-reset";
	}
	
	

}
