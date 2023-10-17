package com.apiserratec.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.dto.LivroResumidoDTO;
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

	public List<LivroResumidoDTO> listarLivrosResumidos() {
		List<Livro> livros = livroRepo.findAll();
		List<LivroResumidoDTO> livrosDTO = new ArrayList<>();

		// percorre lista de livros do tipo Livro e insere os dados em um novo
		// LivroResumido
		for (Livro livro : livros) {
			LivroResumidoDTO livroResDTO = new LivroResumidoDTO(livro.getCodigoLivro(), livro.getNomelivro(),
					livro.getDataLancamento(), livro.getEditora().getNome());

			livrosDTO.add(livroResDTO);
		}
		return livrosDTO;
	}

	public Livro getLivroById(Integer id) {
		return livroRepo.findById(id).orElse(null);
	}

	public LivroResumidoDTO getLivroResumidoPorId(Integer id) {
		Livro livro = livroRepo.findById(id).orElse(null);
		if (livro != null) {
			return new LivroResumidoDTO(livro.getCodigoLivro(), livro.getNomelivro(), livro.getDataLancamento(),
					livro.getEditora().getNome());
		}
		return null;
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

		return livroContinuaExistindo == null;

	}

}
