package com.example.aprochegue.seguranca;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.aprochegue.model.Usuario;

public class UserDetailsImplements implements UserDetails {
	
private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public UserDetailsImplements(Usuario user) {
		this.userName = user.getEmail();
		this.password = user.getSenha();
	}
	
	public UserDetailsImplements() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

//	private static final long serialVersionUID = 1L;
//	
//	private String email;
//	
//	private String senha;
//	
//	 private List<GrantedAuthority> autorization;
//	 
//	 public  UserDetailsImplements(Usuario usuario) {
//		 
//		 this.email = usuario.getEmail();
//		 this.senha = usuario.getSenha();
//	 }
//
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return autorization;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return senha;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return email;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
	
}
