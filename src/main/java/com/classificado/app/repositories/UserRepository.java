package com.classificado.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classificado.app.models.UserModel;

/**
 * UserRepository responsible for CRUD operations on UserModel
 * 
 * @author Boaz
 * @since 26/01/2022
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	/**
	 * Method responsible for find user by email
	 * 
	 * @author Boaz
	 * @date 26/01/2022
	 * @version 1.0
	 * @param email
	 * @return Optional<UserModel>
	 */
	public Optional<UserModel> findByEmail(String email);

}
