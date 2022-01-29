package com.classificado.app.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence UserModel
 * 
 * @author Boaz
 * @since 26/01/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_users")
public class UserModel {
	
	// System generated
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;
    private Long dbCoins = 100L;

	// User generated
	private String name;
	private String email;
	private String password;

	// System relations
	@OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"creator"})
	private List<AdvertsmentModel> adverts = new ArrayList<>();

	// Getters and Setters
	public List<AdvertsmentModel> getAdverts() {
		return this.adverts;
	}

	public void setAdverts(List<AdvertsmentModel> adverts) {
		this.adverts = adverts;
	}
	
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

	public Long getDbCoins() {
		return this.dbCoins;
	}

	public void setDbCoins(Long dbCoins) {
		this.dbCoins = dbCoins;
	}

}
