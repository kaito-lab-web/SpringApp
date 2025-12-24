package com.example.demo.entity;

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
}
