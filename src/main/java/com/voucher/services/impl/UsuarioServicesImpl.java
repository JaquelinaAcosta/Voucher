package com.voucher.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.voucher.model.Usuario;
import com.voucher.repository.UsuarioRepository;
import com.voucher.services.UsuarioService;

import ch.qos.logback.core.boolex.Matcher;

@Service
public class UsuarioServicesImpl implements UsuarioService{
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	private static final Log logger = LogFactory.getLog(UsuarioServicesImpl.class);

	@Override
	public Usuario addUsuario(Usuario usuario) throws Exception {
		logger.info("ALTA IMPRESORA");
		validarUsuario(usuario);
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
		logger.info("MODIFICACIÓN IMPRESORA");
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
}
