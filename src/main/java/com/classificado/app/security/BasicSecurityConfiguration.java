package com.classificado.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class of configure security spring boot
 * 
 * @author Gustavo Boaz
 * @date 29/01/2022
 * @version 1.0
 * @see WebSecurityConfigurerAdapter
 * @see EnableWebSecurity
 * @see PasswordEncoder
 * @see BCryptPasswordEncoder
 * @see SessionCreationPolicy
 * @see HttpSecurity
 * @see AuthenticationManagerBuilder
 * @see HttpMethod
 * @see ResponseStatusException
 * @see HttpStatus
 * @see UserServices
 */
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private @Autowired UserDetailsServiceImplement service;
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests()
 				.antMatchers(HttpMethod.POST, "/api/v1/user/save").permitAll()
 				.antMatchers(HttpMethod.PUT, "/api/v1/user/auth").permitAll()
 				.anyRequest().authenticated()
 			.and().httpBasic()
 			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 			.and().cors()
 			.and().csrf().disable();
 	}
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(service);

		auth.inMemoryAuthentication().withUser("boaz").password(passwordEncoder().encode("boaz"))
		 .authorities("ROLE_ADMIN");
 	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
