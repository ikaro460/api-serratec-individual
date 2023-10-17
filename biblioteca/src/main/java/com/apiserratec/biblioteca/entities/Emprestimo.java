package com.apiserratec.biblioteca.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
	CONSTRAINT alunos_pkey PRIMARY KEY (codigoemprestimo)
*/

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoEmprestimo", scope = Emprestimo.class)

@Entity
@Table(name = "emprestimo")

public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigoemprestimo")
	private Integer codigoEmprestimo;

	/* RELACIONAMENTOS */

	// EMPRESTIMOS > ALUNO
	// @JsonBackReference(value = "aluno-mng-ref")
	@ManyToOne
	@JoinColumn(name = "numeromatriculaaluno", referencedColumnName = "numeromatriculaaluno")
	private Aluno aluno;

	// EMPRESTIMO > LIVRO
	// @JsonBackReference(value = "livro-emprestimo-ref")
	@ManyToOne
	@JoinColumn(name = "codigolivro", referencedColumnName = "codigolivro")
	private Livro livro;

	@Column(name = "dataemprestimo")
	private Date dataEmprestimo;

	@Column(name = "dataentrega")
	private Date dataEntrega;

	@Column(name = "valoremprestimo")
	private Integer valorEmprestimo;

	/* GETTERS E SETTERS */

	public Integer getCodigoEmprestimo() {
		return codigoEmprestimo;
	}

	public void setCodigoEmprestimo(Integer codigoemprestimo) {
		this.codigoEmprestimo = codigoemprestimo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(Integer valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

}
