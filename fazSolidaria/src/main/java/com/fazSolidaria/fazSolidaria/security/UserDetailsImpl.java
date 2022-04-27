package com.fazSolidaria.fazSolidaria.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;


public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L; //é um atributo utilizado para controlar explicitamente a compatibilidade entre o . class usado para serializar e o . class que será utilizado na desserialização.
	
	private String userName;
	private String password;
	
	public UserDetailsImpl(UsuarioModel user) {
		this.userName = user.getNome();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
