package com.voucher.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.voucher.model.Usuario;
import com.voucher.model.Request.updatePasswordRequest;
import com.voucher.model.Request.usuModifRequest;
import com.voucher.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	//modificacion de usuario
	@RequestMapping(value = "/usuario/modificacion", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
	consumes=MediaType.APPLICATION_JSON_VALUE)
	public Usuario update( @RequestBody @Valid usuModifRequest usumodifrequest) throws Exception {
		return usuarioService.updateUsuario(usumodifrequest);	
	}

    @RequestMapping(value="/usuario/modificarPassword",method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
	public Usuario updatePassword(@RequestBody updatePasswordRequest  updatepasswordrequest) throws Exception{
    	try {		
    		if (updatepasswordrequest.getEmail()==null || updatepasswordrequest.getPassword()==null) {
    			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibio datos");
    		}
    		Usuario user = usuarioService.updatePassword(updatepasswordrequest.getEmail(),updatepasswordrequest.getPassword());
    		
    		if(user == null) {
    			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    		}
    		
    		return user;
    	} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.DELETE)
	public Usuario deleteUsuario(@PathVariable String usuarioId){
		return this.usuarioService.deleteUsuario(usuarioId);
	}
	
	@RequestMapping(value = "/usuario/todos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/usuario/{email}", method = RequestMethod.GET)
	public Usuario userDetails(@PathVariable String email) throws Exception {
		Usuario user = usuarioService.getUsuario(email);
		if (user == null) {
			throw new Exception("Usuario inexistente");
		}
		return user;
	}
	
}
