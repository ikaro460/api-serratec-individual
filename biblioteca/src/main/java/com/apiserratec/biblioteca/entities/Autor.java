package com.apiserratec.biblioteca.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
	CONSTRAINT alunos_pkey PRIMARY KEY (codigoautor)
*/

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoAutor")

@Entity
@Table(name = "autor")

public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigoautor")
	private Integer codigoAutor;

	/* RELACIONAMENTOS */
	// AUTOR > LIVRO
	@OneToMany(mappedBy = "autor")
	private List<Livro> livros;

	@Column(name = "nomeautor")
	private String nomeAutor;

	public Integer getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(Integer codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

}
