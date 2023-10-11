package com.apiserratec.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Aluno getAlunoById(Integer id) {
		/*
		 * Optional<Aluno> alunoBanco = alunoRepo.findById(id);
		 * if(alunoBanco.isPresent()) return alunoBanco.get(); else return null;
		 */

		return alunoRepo.findById(id).orElse(null);
	}

	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public Aluno atualizarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);

	}

	public Boolean deletarAluno(Aluno aluno) {
		
		// verifica se aluno é valido
		if(aluno == null)
			return false;
		
		// verifica se existe no banco
		Aluno alunoExistente = getAlunoById(aluno.getNumeroMatriculaAluno());
		if (alunoExistente == null)
			return false;
		
		// deleta aluno
		alunoRepo.delete(aluno);
		
		//verifica se foi deletado de fato
		Aluno alunoContinuaExistindo = getAlunoById(aluno.getNumeroMatriculaAluno());
		if (alunoContinuaExistindo == null)
			return true;
		return false;
	}

}
