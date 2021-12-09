package com.example.aprochegue.servicos;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.aprochegue.model.Usuario;
import com.example.aprochegue.model.dtos.CredentialsDTO;
import com.example.aprochegue.model.dtos.UsuarioLoginDTO;
import com.example.aprochegue.repository.UserRepository;

@Service
public class UserServicos {
	
	
	@Autowired
	private  UserRepository repository;
	
	private static String encriptadorDeSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	//return repository.findByEmail(usuarioParaCadastrar.getEmail()).map(usuarioExistente -> {
	//	return Optional.empty();
	//}).orElseGet(() -> {
	//	usuarioParaCadastrar.setSenha(encriptadorDeSenha(usuarioParaCadastrar.getSenha()));
	//	return Optional.ofNullable(repository.save(usuarioParaCadastrar));
	//});
	public Optional<Usuario> cadastrarUsuario(Usuario usuarioParaCadastrar) {
		if (repository.findByEmail(usuarioParaCadastrar.getEmail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		usuarioParaCadastrar.setSenha(criptografarSenha(usuarioParaCadastrar.getSenha()));
		return Optional.of(repository.save(usuarioParaCadastrar));
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuarioParaAtualizar) {
		return repository.findById(usuarioParaAtualizar.getId()).map(resp -> {
			resp.setNome(usuarioParaAtualizar.getNome());
			resp.setSenha(encriptadorDeSenha(usuarioParaAtualizar.getSenha()));
			return Optional.ofNullable(repository.save(resp));
		}).orElseGet(() -> {
			return Optional.empty();
		});

	}
	
	private static String gerarToken(String email, String senha) {
		String estrutura = email + ":" + senha;
		byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(estruturaBase64);

	}
	
	
	
	public ResponseEntity<CredentialsDTO> pegarCredenciais(UsuarioLoginDTO usuarioParaAutenticar) {
		return repository.findByEmail(usuarioParaAutenticar.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), resp.getSenha())) {

				CredentialsDTO objetoCredenciaisDTO = new CredentialsDTO();

				objetoCredenciaisDTO.setToken(gerarToken(usuarioParaAutenticar.getEmail(), usuarioParaAutenticar.getSenha()));
				objetoCredenciaisDTO.setIdUsuario(resp.getId());
				objetoCredenciaisDTO.setNome(resp.getNome());
				objetoCredenciaisDTO.setFoto(resp.getFoto());
				objetoCredenciaisDTO.setTipo(resp.getTipo());
				objetoCredenciaisDTO.setEmail(resp.getEmail());
				objetoCredenciaisDTO.setSenha(resp.getSenha());

				return ResponseEntity.status(201).body(objetoCredenciaisDTO); // Usuario Credenciado
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Incorreta!"); // Senha incorreta
			}
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!"); // Email não existe
		});
		
	}
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);		
	}
	

}
