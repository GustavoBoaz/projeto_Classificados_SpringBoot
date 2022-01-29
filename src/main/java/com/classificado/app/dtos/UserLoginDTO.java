package com.classificado.app.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Class responsible for DTO Userlogin.
 * 
 * @author Gustavo Boaz
 * @date 29/01/2022
 * @version 1.0
 * @see UserModel
 * @see UserCredentialsDTO
 */
public class UserLoginDTO {

	private @NotBlank @Email String email;
	private @NotBlank String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
