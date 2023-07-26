package com.itvedant.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.studentmanagement.dto.RegisterUserDto;
import com.itvedant.studentmanagement.entity.User;
import com.itvedant.studentmanagement.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//@Autowired
	//private UserDetailsService userDetailsService;

	
	public User register(RegisterUserDto registerUserDto) {
		
		if(this.userRepository.findByEmail(registerUserDto.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exits");
		}
		
		//userDetailsService.loadUserByUsername(registerUserDto.getEmail());
		
		User user = new User();
		
		user.setName(registerUserDto.getName());
		user.setEmail(registerUserDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
		user.setRoles(registerUserDto.getRoles());
		//Syste.out.
		this.userRepository.save(user);
		
		
		return user;
	}
	
}
