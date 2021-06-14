package com.voucher.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.transaction.annotation.Transactional;

import com.voucher.model.Usuario;
import com.voucher.model.Request.userRecoveryPass;
import com.voucher.model.Request.usuModifRequest;


public interface UsuarioService {

	Usuario addUsuario(Usuario usuario) throws Exception;
	Usuario updateUsuario(usuModifRequest usumodifrequest) throws Exception;
	Usuario deleteUsuario(String usuarioId);
	List<Usuario> getUsuarios();
	Usuario getUsuario(String email);
	List<Usuario> getUsuariosEstado();
	String getPassword();
	boolean isAccountNonExpired();
	boolean isAccountNonLocked();
	boolean isCredentialsNonExpired();
	boolean isEnabled();
	Collection<? extends GrantedAuthority> getAuthorities();
	String getEmail();
	UserDetails loadUserByUsername(String email);
	Usuario updatePassword(String email,String password)throws Exception;
	userRecoveryPass recoveryPass(String email,Date expire);
	
	

}
