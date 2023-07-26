package com.itvedant.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.itvedant.studentmanagement.service.MyUserDeatilsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDeatilsService myUserDeatilsService;

	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/student/create").hasAuthority("ADMIN")
				.requestMatchers("/student/edit/{id}").hasAuthority("ADMIN")
				.requestMatchers("/students/delete/{id}").hasAuthority("ADMIN")
				.requestMatchers("/").hasAuthority("USER")
				.anyRequest().authenticated()
				.and()
				.httpBasic(Customizer.withDefaults())
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll())
				.authenticationProvider(daoAuthenticationProvider());
				
				
		
		return http.build();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.myUserDeatilsService);
		provider.setPasswordEncoder(this.passswordEncoder());
		return provider;
	}
	
	
	
	@Bean
	public UserDetailsService users() {
		
		UserDetails user = User.builder()
							.username("user")
							.password(passswordEncoder().encode("user123"))
							.roles("USER")
							.build();
		
		UserDetails admin = User.builder()
								.username("admin")
								.password(passswordEncoder().encode("admin123"))
								.roles("USER" ,"ADMIN")
								.build();
		
		return new InMemoryUserDetailsManager(user, admin);
		
	}
	
	
}
