package com.apiserratec.biblioteca.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.apiserratec.biblioteca.dto.EditoraDTO;
import com.apiserratec.biblioteca.dto.ReceitaWsDTO;
import com.apiserratec.biblioteca.entities.Editora;
import com.apiserratec.biblioteca.exceptions.NoSuchElementException;
import com.apiserratec.biblioteca.repositories.EditoraRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		// return editoraRepo.findById(id).orElse(null);
		return editoraRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Editora", id));
	}

	public Editora salvarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}

	public Editora criarComFoto(String strEditora, MultipartFile arqImg) throws IOException {
        Editora editora = new Editora();
        
        try {
            ObjectMapper objMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
             
            //editora.setImagem(arqImg.getBytes());
            editora = objMapper.readValue(strEditora, Editora.class);
        } catch(IOException e) {
            System.out.println("Erro ao converter a string Editora: " + e.toString());
        }
        editora.setImagemFileName(arqImg.getBytes());
        // fazer o @Lob com um array de bytes
        
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

	
	// METEDOS AUXILIARES
	
	private EditoraDTO convertToDto(Editora editora) {
		return modelMapper.map(editora, EditoraDTO.class);
	}

	private Editora convertToEntity(EditoraDTO editoraDto) {
		return modelMapper.map(editoraDto, Editora.class);
	}

	public ReceitaWsDTO consultaCnpj(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/cnpj";
		Map<String, String> params = new HashMap<String, String>();

		params.put("cnpj", cnpj);

		ReceitaWsDTO receitaDto = restTemplate.getForObject(uri, ReceitaWsDTO.class, params);
		return receitaDto;
	}
/*
	private String construirURLDaImagem(String nomeOriginal) {
        // Aqui, você construirá a URL com base no local onde as imagens são armazenadas.
        // Por exemplo, se estiverem em um diretório no servidor, você pode construir a URL usando o caminho relativo ou absoluto.
        // Se estiver usando um serviço de armazenamento em nuvem, a URL será fornecida por esse serviço.
        // Exemplo:
        // return "http://seuservidor.com/imagens/" + nomeOriginal;
        return "http://localhost:8080/imagens/" + nomeOriginal;
    }

    private String extrairNomeDaImagem(String nomeOriginal) {
        // Você pode extrair o nome da imagem do nome original do arquivo removendo a extensão.
        int lastDotIndex = nomeOriginal.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            return nomeOriginal.substring(0, lastDotIndex);
        }
        return nomeOriginal; // Se não houver extensão, retorne o nome original.
    }
*/
	public Editora salvarEditoraComFoto(String strEditora, MultipartFile arqImg) throws IOException {
		Editora editora = new Editora();

		try {
			ObjectMapper objMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			editora = objMapper.readValue(strEditora, Editora.class);
		} catch (IOException e) {
			System.out.println("Erro ao converter a string: " + e.toString());
		}
		editora.setImagemFileName(arqImg.getBytes());

		return editoraRepo.save(editora);
	}
}
