package com.classificado.app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.classificado.app.models.UserModel;
import com.classificado.app.repositories.UserRepository;

/**
 * Class responsible for service loadUserByUsername.
 * 
 * @author Gustavo Boaz
 * @date 29/01/2022
 * @version 1.0
 * @see UserDetailsService
 * @see UserRepository
 * @see UserModel
 * @see UserDetailsImplement
 */
@Service
public class UserDetailsServiceImplement implements UserDetailsService {
	
	private @Autowired UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> optional =  repository.findByEmail(username);
		
		if (optional.isPresent()) {
			return new UserDetailsImplements(optional.get());
		} else {
			throw new UsernameNotFoundException("Usuario não existe");
		}
	}

}
