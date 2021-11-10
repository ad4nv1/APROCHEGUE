package com.example.aprochegue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aprochegue.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
