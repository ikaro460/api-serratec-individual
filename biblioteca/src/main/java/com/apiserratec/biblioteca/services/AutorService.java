package com.apiserratec.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.entities.Autor;
import com.apiserratec.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {
	// CRUD
	// recuperar todos os autors *
	// recuperar um autor pela sua chave primaria *
	// salvar um novo autor *
	// atualizar um determinado autor *
	// deletar um determinado autor *

	@Autowired
	AutorRepository autorRepo;

	public List<Autor> listarAutores() {
		return autorRepo.findAll();
	}

	public Autor getAutorById(Integer id) {
		/*
		 * Optional<Autor> autorBanco = autorRepo.findById(id);
		 * if(autorBanco.isPresent()) return autorBanco.get(); else return null;
		 */

		return autorRepo.findById(id).orElse(null);
	}

	public Autor salvarAutor(Autor autor) {
		return autorRepo.save(autor);
	}

	public Autor atualizarAutor(Autor autor) {
		return autorRepo.save(autor);

	}

	public Boolean deletarAutor(Autor autor) {
		
		// verifica se autor é valido
		if(autor == null)
			return false;
		
		// verifica se existe no banco
		Autor autorExistente = getAutorById(autor.getCodigoAutor());
		if (autorExistente == null)
			return false;
		
		// deleta autor
		autorRepo.delete(autor);
		
		//verifica se foi deletado de fato
		Autor autorContinuaExistindo = getAutorById(autor.getCodigoAutor());
		if (autorContinuaExistindo == null)
			return true;
		return false;
	}

}
