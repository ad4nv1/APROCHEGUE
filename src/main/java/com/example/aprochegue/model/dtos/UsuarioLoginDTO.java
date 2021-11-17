package com.example.aprochegue.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioLoginDTO {
	
	@NotBlank
	@Email (message = "O campo deve ser preenchido com um email")
	private String email;
	
	@NotBlank 
	@Size (min = 4, max = 32, message = "Deve conter 4 a 32 caracteres")
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	} 
	
}
