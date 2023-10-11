package com.apiserratec.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiserratec.biblioteca.entities.Emprestimo;
import com.apiserratec.biblioteca.repositories.EmprestimoRepository;

@Service
public class EmprestimoService {
	// CRUD
	// recuperar todos os alunos *
	// recuperar um aluno pela sua chave primaria *
	// salvar um novo aluno *
	// atualizar um determinado aluno *
	// deletar um determinado aluno *
	
	@Autowired
	EmprestimoRepository emprestimoRepo;
	
	public List<Emprestimo> listarEmprestimos() {
		return emprestimoRepo.findAll();
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepo.findById(id).orElse(null);
	}
	
	public Emprestimo salvarEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepo.save(emprestimo);
	}
	
	public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepo.save(emprestimo);
	}
	
	public boolean deletarEmprestimo(Emprestimo emprestimo) {
		// verifica se emprestimo é valido
		if (emprestimo == null)
			return false;

		// verifica se existe no banco
		Emprestimo emprestimoExistente = getEmprestimoById(emprestimo.getCodigoEmprestimo());
		if (emprestimoExistente == null)
			return false;

		// deleta emprestimo
		emprestimoRepo.delete(emprestimo);

		// verifica se foi deletado de fato
		Emprestimo emprestimoContinuaExistindo = getEmprestimoById(emprestimo.getCodigoEmprestimo());
		if (emprestimoContinuaExistindo == null)
			return true;
		return false;
	}
	
}
