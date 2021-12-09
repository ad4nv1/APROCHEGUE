package com.example.aprochegue.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.aprochegue.model.Usuario;
import com.example.aprochegue.repository.UserRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<Usuario> user = userRepository.findByEmail(userName);
		
		user.orElseThrow(()-> new UsernameNotFoundException(userName+ " not found."));
		
		return user.map(UserDetailsImplements::new).get();
	
	}

//	@Autowired
//	private UserRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Usuario> objetoOptional = repository.findByEmail(username);
//		if (objetoOptional.isPresent()) {
//			return new UserDetailsImplements(objetoOptional.get());
//		}
//		else {
//			throw new UsernameNotFoundException(username+"Usuario não existe!");
//		}
//	}
	
	
}
