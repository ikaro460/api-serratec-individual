package com.apiserratec.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiserratec.biblioteca.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	
}
