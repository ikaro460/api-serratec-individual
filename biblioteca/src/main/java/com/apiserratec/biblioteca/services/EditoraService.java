package com.apiserratec.biblioteca.services;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.dto.EditoraDTO;
import com.apiserratec.biblioteca.entities.Editora;
import com.apiserratec.biblioteca.repositories.EditoraRepository;

@Service
public class EditoraService {
	// CRUD
	// recuperar todos os alunos *
	// recuperar um aluno pela sua chave primaria *
	// salvar um novo aluno *
	// atualizar um determinado aluno *
	// deletar um determinado aluno *

	@Autowired
	EditoraRepository editoraRepo;
	
    @Autowired
    private ModelMapper modelMapper;

	public List<Editora> listarEditoras() {
		return editoraRepo.findAll();
	}

	public Editora getEditoraById(Integer id) {
		return editoraRepo.findById(id).orElse(null);
	}

	public Editora salvarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}

	public EditoraDTO salvarEditoraDTO(EditoraDTO editoraDTO) {
		
		// converte editora
	    Editora editora = convertToEntity(editoraDTO);
	    
	    // salva editora
		editoraRepo.save(editora);
		
		// retorna dto
		return convertToDto(editora);
	}

	public Editora atualizarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}

	public boolean deletarEditora(Editora editora) {
		// verifica se editora é valido
		if (editora == null)
			return false;

		// verifica se existe no banco
		Editora editoraExistente = getEditoraById(editora.getCodigoEditora());
		if (editoraExistente == null)
			return false;

		// deleta editora
		editoraRepo.delete(editora);

		// verifica se foi deletado de fato
		Editora editoraContinuaExistindo = getEditoraById(editora.getCodigoEditora());
		if (editoraContinuaExistindo == null)
			return true;
		return false;
	}
	
	private EditoraDTO convertToDto(Editora editora) {
	    return modelMapper.map(editora, EditoraDTO.class);
	}
	
	private Editora convertToEntity(EditoraDTO editoraDto) {
	    return modelMapper.map(editoraDto, Editora.class);
	}

}
