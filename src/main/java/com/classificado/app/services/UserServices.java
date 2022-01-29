package com.classificado.app.services;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.classificado.app.dtos.UserCredentialsDTO;
import com.classificado.app.dtos.UserLoginDTO;
import com.classificado.app.models.UserModel;
import com.classificado.app.repositories.UserRepository;

/**
 * Class responsible for the user services.
 * 
 * @author Gustavo Boaz
 * @date 29/01/2022
 * @version 1.0
 */
@Service
public class UserServices {
	
	private UserCredentialsDTO credentialsDTO;
	private @Autowired UserRepository repository;
	
	/**
	 * Private static method, used to encrypt with BCryptPasswordEncoder format a
	 * string passed as a parameter.
	 * 
	 * @param password, String format.
	 * @return String
	 * @author Boaz
	 * @since 1.0
	 * @see BCryptPasswordEncoder
	 * 
	 */
	private static String encryptPaswword(String password) {
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	/**
	 * Private static method, used to generate basic token.
	 * 
	 * @param email,    String format.
	 * @param password, String format.
	 * @return String
	 * @author Boaz
	 * @since 1.0
	 * @see Base64
	 * 
	 */
	private static String generatorBasicToken(String email, String password) {
		String structure = email + ":" + password; //isabel@email.com:134652
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII"))); // aXNhYmVsQGVtYWlsLmNvbToxMzQ2NTI=
		return "Basic " + new String(structureBase64); // Basic aXNhYmVsQGVtYWlsLmNvbToxMzQ2NTI=
	}
	
	/**
	 * Public method used to register a user in the system's database. This method
	 * returns a BAD_REQUEST if the intention to register already has an email
	 * registered in the system, to avoid duplication. If you don't hear an existing
	 * email in the system, it returns CREATED status with user object no response.
	 * 
	 * @param newUser, UserRegistrationDTO object.
	 * @return ResponseEntity<UserModel> 
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserModel> registerNewUser(UserModel newUser){
		Optional<UserModel> optional = repository.findByEmail(newUser.getEmail());
		
		if (optional.isEmpty()) {
			newUser.setPassword(encryptPaswword(newUser.getPassword()));
			return ResponseEntity.status(201).body(repository.save(newUser));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email existente!");
		}
	}

	/**
	 * Public method to get user credentials for authentications.
	 * 
	 * @param dto
	 * @return ResponseEntity<UserCredentialsDTO>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserCredentialsDTO> credentials(@Valid UserLoginDTO dto) {
		return repository.findByEmail(dto.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (encoder.matches(dto.getPassword(), resp.getPassword())) {
				credentialsDTO = new UserCredentialsDTO(
						generatorBasicToken(dto.getEmail(), dto.getPassword()),
						resp.getIdUser(),
						resp.getEmail());
				return ResponseEntity.status(200).body(credentialsDTO);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Invalida");
			}
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe no sistema!");
		});
	}

}
