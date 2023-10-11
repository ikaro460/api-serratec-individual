package com.apiserratec.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.entities.Livro;
import com.apiserratec.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {
	// CRUD
	// recuperar todos os livros *
	// recuperar um livro pela sua chave primaria *
	// salvar um novo livro *
	// atualizar um determinado livro *
	// deletar um determinado livro *

	@Autowired
	LivroRepository livroRepo;

	public List<Livro> listarLivros() {
		return livroRepo.findAll();
	}

	public Livro getLivroById(Integer id) {
		return livroRepo.findById(id).orElse(null);
	}

	public Livro salvarLivro(Livro livro) {
		return livroRepo.save(livro);
	}

	public Livro atualizarLivro(Livro livro) {
		return livroRepo.save(livro);
	}

	public boolean deletarLivro(Livro livro) {

		// verifica se livro é valido
		if (livro == null)
			return false;

		// verifica se existe no banco
		Livro livroExistente = getLivroById(livro.getCodigoLivro());
		if (livroExistente == null)
			return false;

		// deleta livro
		livroRepo.delete(livro);

		// verifica se foi deletado de fato
		Livro livroContinuaExistindo = getLivroById(livro.getCodigoLivro());
		if (livroContinuaExistindo == null)
			return true;
		return false;

	}

}
