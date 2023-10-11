package com.apiserratec.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiserratec.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
