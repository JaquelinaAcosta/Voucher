package com.voucher.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.model.Usuario;
import com.voucher.services.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsuarioService usuarioService;
	
	
	//alta usuario
	@RequestMapping(value = "/usuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
			public Usuario addUsuario(@RequestBody @Valid Usuario usuario) throws Exception {
				return usuarioService.addUsuario(usuario);	
			}
	
	//modificacion de usuario
	@RequestMapping(value = "/usuario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
	consumes=MediaType.APPLICATION_JSON_VALUE)
	public Usuario updateUsuario(@RequestBody @Valid Usuario usuario) throws Exception {
		return usuarioService.updateUsuario(usuario);	
	}
	
	//baja usuario
	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.DELETE)
	public Usuario deleteUsuario(@PathVariable String usuarioId){
		return this.usuarioService.deleteUsuario(usuarioId);
	}
	
	//listado de usuarios
	@RequestMapping(value = "/usuario", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> getUsuarios()
	{
		return usuarioService.getUsuarios();
	}
	
}
