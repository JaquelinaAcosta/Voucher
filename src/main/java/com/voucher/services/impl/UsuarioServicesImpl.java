package com.voucher.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voucher.model.Role;
import com.voucher.model.Usuario;
import com.voucher.repository.RoleRepository;
import com.voucher.repository.UsuarioRepository;
import com.voucher.services.UsuarioService;

import ch.qos.logback.core.boolex.Matcher;

@Service
@EnableWebSecurity
public class UsuarioServicesImpl implements UsuarioService{
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static final Log logger = LogFactory.getLog(UsuarioServicesImpl.class);

	@Override
	public Usuario addUsuario(Usuario usuario) throws Exception {
		logger.info("ALTA USUARIO");
		validarUsuario(usuario);
		usuario.setEstado(true);
		// Role userRole = roleRepository.findByRole("ADMIN");
		// usuario.setRole(new HashSet<>(Arrays.asList(userRole)));
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
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
		Usuario usuarioUpdate = usuarioRepository.save(usuario);
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
}
