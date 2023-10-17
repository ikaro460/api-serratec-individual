package com.apiserratec.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.dto.AlunoResumidoDTO;
import com.apiserratec.biblioteca.entities.Aluno;
import com.apiserratec.biblioteca.repositories.AlunoRepository;

@Service
public class AlunoService {
	// CRUD
	// recuperar todos os alunos *
	// recuperar um aluno pela sua chave primaria *
	// salvar um novo aluno *
	// atualizar um determinado aluno *
	// deletar um determinado aluno *

	@Autowired
	AlunoRepository alunoRepo;

	public List<Aluno> listarAlunos() {
		return alunoRepo.findAll();
	}

	public List<AlunoResumidoDTO> listarAlunosResumidos() {
		List<Aluno> alunos = alunoRepo.findAll();
		List<AlunoResumidoDTO> alunosDTO = new ArrayList<>();

		// percorre lista de alunos do tipo Aluno e insere os dados em um novo
		// AlunoResumido
		for (Aluno aluno : alunos) {
			AlunoResumidoDTO alunoResDTO = new AlunoResumidoDTO();
			alunoResDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
			alunoResDTO.setNome(aluno.getName());
			alunoResDTO.setCpf(aluno.getCpf());

			alunosDTO.add(alunoResDTO);
		}
		return alunosDTO;
	}

	public Aluno getAlunoById(Integer id) {
		/*
		 * Optional<Aluno> alunoBanco = alunoRepo.findById(id);
		 * if(alunoBanco.isPresent()) return alunoBanco.get(); else return null;
		 */

		return alunoRepo.findById(id).orElse(null);
	}

	public AlunoResumidoDTO getAlunoResumidoPorId(Integer id) {
		Aluno aluno = alunoRepo.findById(id).orElse(null);
		if (aluno != null) {
			return new AlunoResumidoDTO(aluno.getNumeroMatriculaAluno(), aluno.getName(), aluno.getCpf());
		}
		return null;
	}

	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public Aluno atualizarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);

	}

	public Boolean deletarAluno(Aluno aluno) {

		// verifica se aluno é valido
		if (aluno == null)
			return false;

		// verifica se existe no banco
		Aluno alunoExistente = getAlunoById(aluno.getNumeroMatriculaAluno());
		if (alunoExistente == null)
			return false;

		// deleta aluno
		alunoRepo.delete(aluno);

		// verifica se foi deletado de fato
		Aluno alunoContinuaExistindo = getAlunoById(aluno.getNumeroMatriculaAluno());
		if (alunoContinuaExistindo == null)
			return true;
		return false;
	}

}
