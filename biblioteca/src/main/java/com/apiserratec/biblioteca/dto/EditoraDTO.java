package com.apiserratec.biblioteca.dto;

public class EditoraDTO {

	private Integer codigoEditora;
	private String nome;
	

	// standard getters and setters

	
	
	
	public EditoraDTO() {
	}

	public EditoraDTO(Integer codigoEditora, String nome) {
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
