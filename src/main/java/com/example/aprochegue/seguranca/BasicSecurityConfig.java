package com.example.aprochegue.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication()
		.withUser("APROCHEGUE")
		.password(passwordEncoder().encode("APROCHEGUE"))
		.authorities("ROLE_ADMIN");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
	
	

}

//	@Autowired
//	private UserDetailsServiceImplements service ;
//	
//	@Override
//	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(service);
//		
//		auth.inMemoryAuthentication()
//			.withUser("APROCHEGUE").password(passwordEncoder().encode("APROCHEGUE"))
//			.authorities("ROLE_ADMIN");
//		}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	@Override
//	protected void configure (HttpSecurity http) throws Exception {
//		http
//		.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/api/v1/usuario/salvar").permitAll()
//			.antMatchers(HttpMethod.PUT, "/api/v1/usuario/credenciais").permitAll()
//		.anyRequest().authenticated()
//		.and().httpBasic()
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().cors()
//		.and().csrf().disable();
//	}
	
//}
