package com.classificado.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.classificado.app.dtos.UserCredentialsDTO;
import com.classificado.app.dtos.UserLoginDTO;
import com.classificado.app.models.UserModel;
import com.classificado.app.repositories.UserRepository;
import com.classificado.app.services.UserServices;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private @Autowired UserRepository repository;
	private @Autowired UserServices services;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> findAll(){
		List<UserModel> list = repository.findAll();
		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
		
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<UserModel> findById(@PathVariable(value = "id_user") Long idUser) {
		return repository.findById(idUser)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@PostMapping("/save")
	public ResponseEntity<UserModel> save(@Valid @RequestBody UserModel newUser) {
		return services.registerNewUser(newUser);
	}
	
	@PutMapping("/auth")
	public ResponseEntity<UserCredentialsDTO> getCredentials(@Valid @RequestBody UserLoginDTO dto){
		return services.credentials(dto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserModel> update(@Valid @RequestBody UserModel user){
		return repository.findById(user.getIdUser())
				.map(resp -> ResponseEntity.status(200).body(repository.save(user)))
				.orElseGet(() -> {
					 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") Long idUser) {
		Optional<UserModel> optional = repository.findById(idUser);
		
		if (optional.isPresent()) {
			repository.deleteById(idUser);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
	}

}
