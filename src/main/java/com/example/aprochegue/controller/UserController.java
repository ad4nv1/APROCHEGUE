package com.example.aprochegue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.aprochegue.model.Usuario;
import com.example.aprochegue.model.dtos.CredentialsDTO;
import com.example.aprochegue.model.dtos.UsuarioLoginDTO;
import com.example.aprochegue.repository.UserRepository;
import com.example.aprochegue.servicos.UserServicos;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(allowedHeaders = "*", origins = "*")

public class UserController {

	private @Autowired UserRepository repositorio;
	private @Autowired UserServicos servicos;

	@GetMapping("/todes")
	public ResponseEntity<List<Usuario>> getTodes() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/name/{nome_usuario}")
	public ResponseEntity<List<Usuario>> getByNameI(@PathVariable(value = "nome_usuario") String nome) {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/research")
	public ResponseEntity<List<Usuario>> GetByNameII(@RequestParam(defaultValue = "") String nome) {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repositorio.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp)).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"ID invalido, digite novamente.");
		});

	}

	//return servicos.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
	//		.orElseThrow(() -> {
		//		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
		//				"Email existente, cadastre outro email!.");
		//	});
	@PostMapping("/save")
	public ResponseEntity<Object> save(@Valid @RequestBody Usuario novoUsuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(servicos.cadastrarUsuario(novoUsuario).get());

	}

	@PutMapping("/credentials")
	public ResponseEntity<CredentialsDTO> credentials(@Valid @RequestBody UsuarioLoginDTO userAutentication) {
		return servicos.pegarCredenciais(userAutentication);
	}

	@PutMapping("/update")
	public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario newUser) {
		return servicos.atualizarUsuario(newUser).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Digite um Id válido.");
				});

	}

	@DeleteMapping("/delete/{id_usuario}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repositorio.findById(idUsuario).map(resp -> {
			repositorio.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Digite um Id válido.");
		});
	}

}
