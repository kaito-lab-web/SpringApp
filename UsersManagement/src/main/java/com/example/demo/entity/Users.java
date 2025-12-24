package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Users {
	
	@Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String role;
    private String name;
    private String mail;
    
    @Column(name = "add_date")
    private LocalDateTime addDate;
    
    @Column(name = "account_enabled")
    private boolean addountEnable;
    
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
    
    //emailaccount_enabled
    //upd_date
    //last_login_date
}
