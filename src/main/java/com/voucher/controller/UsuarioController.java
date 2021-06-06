package com.voucher.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.model.Usuario;
import com.voucher.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	//alta usuario
	/*@RequestMapping(value = "/usuario/alta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
	consumes=MediaType.APPLICATION_JSON_VALUE)
	public Usuario save(@RequestBody @Valid Usuario usuario) throws Exception {
		return usuarioService.addUsuario(usuario);	
	}*/
	
	//modificacion de usuario
	@RequestMapping(value = "/usuario/modificacion", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
	consumes=MediaType.APPLICATION_JSON_VALUE)
	public Usuario update(@RequestBody @Valid Usuario usuario) throws Exception {
		return usuarioService.updateUsuario(usuario);	
	}

	//baja usuario
	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.DELETE)
	//@PreAuthorize("hasRole('ADMIN')")
	public Usuario deleteUsuario(@PathVariable String usuarioId){
		return this.usuarioService.deleteUsuario(usuarioId);
	}
	
	//listado de usuarios
	@RequestMapping(value = "/usuario/todos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('USER')")
	public List<Usuario> getUsuarios()
	{
		List<Usuario> usuarios = usuarioService.getUsuarios();
		ArrayList<Usuario> usuariosReturn = new ArrayList<Usuario>();
		// && usu.getEmpresa() != null
		for(Usuario usu: usuarios) {
			if(usu.getRoles() != null) {
				usuariosReturn.add(usu);
			}
		}
		
		return usuariosReturn;
	}	
	
	//Usuario logueado
	@RequestMapping(value = "/usuario/{email}", method = RequestMethod.GET)
	public Usuario userDetails(@PathVariable String email) throws Exception {
		Usuario user = usuarioService.getUsuario(email);
		if (user == null) {
			throw new Exception("Usuario inexistente");
		}
		return user;
	}	

	//@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/usuario/admin", method = RequestMethod.GET)
	public String moderatorAccess() {
		return "Moderator Board.";
	}

}
