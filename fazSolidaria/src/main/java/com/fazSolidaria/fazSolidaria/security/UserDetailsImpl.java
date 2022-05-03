package com.fazSolidaria.fazSolidaria.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fazSolidaria.fazSolidaria.model.UsuarioModel;


public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L; 
	
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
	
}
