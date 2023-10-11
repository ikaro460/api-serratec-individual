package com.apiserratec.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiserratec.biblioteca.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
