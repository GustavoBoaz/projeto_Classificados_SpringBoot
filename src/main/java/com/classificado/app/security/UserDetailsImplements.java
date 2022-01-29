package com.classificado.app.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.classificado.app.models.UserModel;

/**
 * Class responsible for charge user details in security spring
 * 
 * @author Gustavo Boaz
 * @date 29/01/2022
 * @version 1.0
 * @see UserDetails
 * @see UserModel
 * @see UserDetailsImplements
 * @see UserDetailsServiceImplement
 */
public class UserDetailsImplements implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImplements() {
		super();
	}

	public UserDetailsImplements(UserModel user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
