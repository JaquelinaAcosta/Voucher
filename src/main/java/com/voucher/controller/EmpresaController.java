package com.voucher.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.model.Empresa;
import com.voucher.services.EmpresaService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmpresaController {
	
	@Autowired
	EmpresaService empresaService;
	
		//alta empresa
		@RequestMapping(value = "/empresa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
		consumes=MediaType.APPLICATION_JSON_VALUE)
		public Empresa addUsuario(@RequestBody @Valid Empresa empresa) throws Exception {
			return empresaService.addEmpresa(empresa);	
		}
		
		//modificacion empresa
		@RequestMapping(value = "/empresa", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
		consumes=MediaType.APPLICATION_JSON_VALUE)
		public Empresa updateEmpresa(@RequestBody @Valid Empresa empresa) throws Exception {
			return empresaService.updateEmpresa(empresa);	
		}
		
		//baja empresa
		@RequestMapping(value = "/empresa/{empresaId}", method = RequestMethod.DELETE)
		public Empresa deleteEmpresa(@PathVariable String empresaId){
			return this.empresaService.deleteEmpresa(empresaId);
		}
		
		//listado de empresas activas e inactivas
		@RequestMapping(value = "/empresa/todas", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public List<Empresa> getEmpresas()
		{
			return empresaService.getEmpresas();
		}
		
		//listado de empresas activas
		public List<Empresa> getEmpresasActivas()
		{
			return empresaService.getEmpresasestado();
		}
}
