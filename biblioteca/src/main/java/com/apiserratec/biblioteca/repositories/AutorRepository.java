package com.apiserratec.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiserratec.biblioteca.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
