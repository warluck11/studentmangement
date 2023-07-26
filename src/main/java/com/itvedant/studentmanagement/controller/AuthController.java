package com.itvedant.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itvedant.studentmanagement.dto.Logindto;
import com.itvedant.studentmanagement.dto.RegisterUserDto;
import com.itvedant.studentmanagement.entity.User;
import com.itvedant.studentmanagement.service.MyUserDeatilsService;
import com.itvedant.studentmanagement.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/user/reg")
	public String reg(Model model) {
		model.addAttribute("userform", new User());
		return "adduser";
	}

	@PostMapping("/user/add")
	public String register(Model model,@ModelAttribute RegisterUserDto registerUserDto) {
		model.addAttribute("userform", new User());
		this.userService.register(registerUserDto);
		return "redirect:/user/reg";
	}
	
	@GetMapping("/login")
	public String log() {
		return "login";
	}
	
	
	@PostMapping("/log")
	public String login(@ModelAttribute Logindto logindto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return "redirect:/";
			
	}
	
	
}
