package com.itvedant.studentmanagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegisterUserDto {
	
	private String name;
	private String email;
	private String password;
	private List<String> roles;

}
