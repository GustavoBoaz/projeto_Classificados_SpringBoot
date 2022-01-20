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

import com.classificado.app.models.User;
import com.classificado.app.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private @Autowired UserRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> findAllUsers(){
		List<User> list = repository.findAll();
		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
		
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<User> findByIdUser(@PathVariable(value = "id_user") Long idUser) {
		return repository.findById(idUser)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User newUser) {
		return ResponseEntity.status(201).body(repository.save(newUser));
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
		return repository.findById(user.getIdUser())
				.map(resp -> ResponseEntity.status(200).body(repository.save(user)))
				.orElseGet(() -> {
					 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id_user}")
	public ResponseEntity deleteUser(@PathVariable(value = "id_user") Long idUser) {
		Optional<User> optional = repository.findById(idUser);
		
		if (optional.isPresent()) {
			repository.deleteById(idUser);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
	}

}
