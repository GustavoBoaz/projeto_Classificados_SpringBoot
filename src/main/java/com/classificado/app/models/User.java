package com.classificado.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *  Classe Modelo para persistencia no Banco de Dados
 * 
 * <p>
 * -idUser: Chave primaria da tabela. Tipo Long
 * </p>
 * <p>
 * - name: Coluna nome da tabela tb_user. Tipo String
 * </p>
 * 
 * @author Boaz
 * @author Bruno
 * @since 21/01/2022
 * @see UserRepository
 * @see UserController
 */
@Entity
@Table(name = "tb_users")
public class User {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;
	private String name;
	private @NotBlank @Email String email;
	private String password;

	/**
	 * @author Bruno
	 * @since 21/01/2022
	 * 
	 * <p> Essa idade é do tipo Long
	 * </p>
	 */
	private Long idade;
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
