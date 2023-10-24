package com.apiserratec.biblioteca.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apiserratec.biblioteca.dto.EditoraDTO;
import com.apiserratec.biblioteca.dto.ReceitaWsDTO;
import com.apiserratec.biblioteca.entities.Editora;
import com.apiserratec.biblioteca.services.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

	@Autowired
	EditoraService editoraService;

	@GetMapping
	public ResponseEntity<List<Editora>> listarEditoras() {
		return new ResponseEntity<>(editoraService.listarEditoras(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Editora> buscarPorId(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		return new ResponseEntity<>(editora, HttpStatus.OK);
		/*
		if(editora == null)
			return new ResponseEntity<>(editora, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(editora, HttpStatus.OK);
			*/	
	}

	@PostMapping
	public ResponseEntity<Editora> salvar(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.salvarEditora(editora), HttpStatus.OK);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<EditoraDTO> salvarDTO(@RequestBody EditoraDTO editoraDTO) {
		return new ResponseEntity<>(editoraService.salvarEditoraDTO(editoraDTO), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Editora> atualizar(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.atualizarEditora(editora), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarEditor(@RequestBody Editora editora) {
		if (editoraService.deletarEditora(editora)) {
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("consulta-cnpj/{cnpj}")
	public ResponseEntity<ReceitaWsDTO> consultaCnpj(@PathVariable String cnpj) {
		return new ResponseEntity<>(editoraService.consultaCnpj(cnpj), HttpStatus.OK);
	}
	
	@PostMapping("/comfoto")
	public ResponseEntity<Editora> criarComFoto(@RequestPart("edt") String strEditora,
			@RequestPart("img") MultipartFile arqImg) throws IOException {
		return new ResponseEntity<>(editoraService.salvarEditoraComFoto(strEditora, arqImg), HttpStatus.CREATED);
	}

}
