package com.classificado.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_anuncios")
public class Anuncio {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idAnuncio;
	private @NotBlank String titulo;
	private @NotBlank String descricao;

	@ManyToOne
	@JoinColumn(name = "fk_user")
	@JsonIgnoreProperties("meusAnuncios")
	private User criador;

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public User getCriador() {
		return criador;
	}

	public void setCriador(User criador) {
		this.criador = criador;
	}

}
