package com.example.aprochegue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aprochegue.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario(String usuario);
	
	public Optional<Usuario> findByNome(String nome);
	
	public List<Usuario> findAllByNomeContainingIgnoreCase (String nome);
	
}
