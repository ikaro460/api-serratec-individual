package com.apiserratec.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiserratec.biblioteca.dto.LivroResumidoDTO;
import com.apiserratec.biblioteca.entities.Livro;
import com.apiserratec.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroService livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> listarLivros() {
		return new ResponseEntity<>(livroService.listarLivros(), HttpStatus.OK);
	}

	@GetMapping("/resumido")
	public ResponseEntity<List<LivroResumidoDTO>> listarLivrosResumidos() {
		return new ResponseEntity<>(livroService.listarLivrosResumidos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id) {
		Livro livro = livroService.getLivroById(id);

		if (livro == null)
			return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(livro, HttpStatus.OK);
	}

	@GetMapping("/resumido/{id}")
	public ResponseEntity<LivroResumidoDTO> getLivroResumidoPoiId(@PathVariable Integer id) {

		LivroResumidoDTO livroResDTO = livroService.getLivroResumidoPorId(id);

		if (livroResDTO == null)
			return new ResponseEntity<>(livroResDTO, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(livroResDTO, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Livro> salvar(@RequestBody Livro livro) {
		return new ResponseEntity<>(livroService.salvarLivro(livro), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Livro> atualizar(@RequestBody Livro livro) {
		return new ResponseEntity<>(livroService.atualizarLivro(livro), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarLivro(@RequestBody Livro livro) {
		if (livroService.deletarLivro(livro)) {
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
		}
	}

}
