package com.voucher.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voucher.model.Usuario;
import com.voucher.repository.RoleRepository;
import com.voucher.repository.UsuarioRepository;

public class UserDetailsImpl  implements UserDetails{
	
	private String id;
	private String email;
	private String Username;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(String id,String email,String username,String password,Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.Username=username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Usuario user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.get_id(), 
				user.getEmail(),
				user.getNombre(),
				user.getPassword(), 
				authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
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
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
}
