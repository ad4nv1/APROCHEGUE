package com.example.aprochegue.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprochegue.model.dtos.UsuarioLoginDTO;
import com.example.aprochegue.model.Usuario;
import com.example.aprochegue.repository.UserRepository;
import com.example.aprochegue.servicos.UserServicos;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserServicos usuarioService;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllUsuario(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
	        return repository.findById(id)
		        .map(resp -> ResponseEntity.ok(resp))
		        .orElse(ResponseEntity.notFound().build());
	}

	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLoginDTO> Autentication(@Valid @RequestBody Optional<UsuarioLoginDTO> user){
		return usuarioService.logarUsuario(user).map(resp -> ResponseEntity.ok(resp))
		        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario usuario){
		 return ResponseEntity.status(HttpStatus.CREATED)
			        .body(usuarioService.cadastrarUsuario(usuario).get());
	}
	
	@PostMapping("/atualizar")
	public ResponseEntity<Usuario> Put(@Valid @RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
