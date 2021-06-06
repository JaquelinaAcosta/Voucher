package com.voucher.services.impl;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.voucher.model.Usuario;
import com.voucher.repository.RoleRepository;
import com.voucher.repository.UsuarioRepository;
import com.voucher.services.UsuarioService;



@Service
@EnableWebSecurity
public class UsuarioServicesImpl implements UsuarioService{
	
private static final Log logger = LogFactory.getLog(UsuarioServicesImpl.class);
	@Inject
	private UsuarioRepository usuarioRepository;
	@JsonIgnore
	private String password;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsuarioServicesImpl() {
	}
	
	@Override
	public Usuario addUsuario(Usuario usuario) throws Exception {
		logger.info("ALTA USUARIO");
		validarUsuario(usuario);
		usuario.setEstado(true);
		// Role userRole = roleRepository.findByRole("ADMIN");
		// usuario.setRole(new HashSet<>(Arrays.asList(userRole)));
		//usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		Usuario usuarioAdd = usuarioRepository.save(usuario);
		return usuarioAdd;
	}

	private void validarUsuario(Usuario usuario) throws Exception{
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		java.util.regex.Matcher matcherEmail = pattern.matcher(usuario.getEmail());
		
		if(matcherEmail.find()==true) {
			logger.info("ALTA/MODIFICACION EXITOSA");
		} else {
			logger.error("HUBO UN ERROR");
			throw new Exception ("Error al agregar/modificar usuario. Correo electrónico inválido");	
		}
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) throws Exception {
		logger.info("MODIFICACIÓN USUARIO");
		validarUsuario(usuario);
		Usuario update=usuarioRepository.findByEmail(usuario.getEmail());
		update.setApellido(usuario.getApellido());
		update.setEmpresa(usuario.getEmpresa());
		update.setEstado(usuario.getEstado());
		update.setNombre(usuario.getNombre());
		update.setRoles(usuario.getRoles());
		update.setTelefono(usuario.getTelefono());
		Usuario usuarioUpdate = usuarioRepository.save(update);
		
		
		return usuarioUpdate;
	}

	@Override
	public Usuario deleteUsuario(String usuarioId) {
		Optional<Usuario> user = usuarioRepository.findById(usuarioId);
		Usuario u = user.get();
		u.setEstado(false);
		return usuarioRepository.save(u);
	}

	@Override
	public List<Usuario> getUsuarios() {
			List<Usuario> usuarios = usuarioRepository.findAll();
			return usuarios;
	}

	@Override
	public List<Usuario> getUsuariosEstado() {
		List<Usuario> usuarios = usuarioRepository.findByEstado(true);
		return usuarios;
	}

	@Override
	public Usuario getUsuario(String email) {
		return usuarioRepository.findByEmail(email);
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void  updatePassword(String email, String password)throws Exception{
		// TODO Auto-generated method stub
		Usuario user = usuarioRepository.findByEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		usuarioRepository.save(user);
	}
}
