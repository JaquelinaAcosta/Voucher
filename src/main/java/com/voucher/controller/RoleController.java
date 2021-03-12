package com.voucher.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.model.Role;
import com.voucher.model.Usuario;
import com.voucher.services.RoleService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	//alta rol
	@RequestMapping(value = "/role", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
	consumes=MediaType.APPLICATION_JSON_VALUE)
	public Role addRole(@RequestBody @Valid Role role) throws Exception {
		return roleService.addRole(role);	
	}
	
	//Obtiene todos los roles
	@RequestMapping(value = "/role/todos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Role> getRoles() {
		return roleService.getRoles();	
	}

}
