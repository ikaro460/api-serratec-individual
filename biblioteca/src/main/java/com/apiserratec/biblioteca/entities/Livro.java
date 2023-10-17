package com.apiserratec.biblioteca.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
	CONSTRAINT alunos_pkey PRIMARY KEY (codigolivro)
*/

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoLivro", scope = Livro.class)

@Entity
@Table(name = "livro")

public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigolivro")
	private Integer codigoLivro;

	/* RELACIONAMENTOS */

	// LIVRO > EDITORA
	// @JsonBackReference(value = "editora-livro-ref")
	@ManyToOne
	@JoinColumn(name = "codigoeditora", referencedColumnName = "codigoeditora")
	private Editora editora;

	// LIVRO > AUTOR
	@ManyToOne
	@JoinColumn(name = "codigoautor", referencedColumnName = "codigoautor")
	private Autor autor;

	// LIVRO > EMPRESTIMO
	// @JsonManagedReference(value = "livro-emprestimo-ref")
	@OneToMany(mappedBy = "livro")
	private List<Emprestimo> emprestimos;

	@Column(name = "nomelivro")
	private String nomelivro;

	@Column(name = "datalancamento")
	private Date dataLancamento;

	@Column(name = "codigoisbn")
	private Integer codigoIsbn;

	public Integer getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(Integer codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public String getNomelivro() {
		return nomelivro;
	}

	public void setNomelivro(String nomelivro) {
		this.nomelivro = nomelivro;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(Integer codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Livro [codigoLivro=" + codigoLivro + ", \neditora=" + editora + ", \nemprestimos=" + emprestimos
				+ ", \nnomelivro=" + nomelivro + ", \nnomeAutor=" + autor + ", \ndataLancamento=" + dataLancamento
				+ ", \ncodigoIsbn=" + codigoIsbn + "]";
	}
}
