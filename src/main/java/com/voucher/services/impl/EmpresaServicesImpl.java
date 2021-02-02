package com.voucher.services.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.voucher.model.Empresa;
import com.voucher.repository.EmpresaRepository;
import com.voucher.services.EmpresaService;

@Service
public class EmpresaServicesImpl implements EmpresaService {
	
	@Inject
	private EmpresaRepository empresaRepository;
	
	private static final Log logger = LogFactory.getLog(UsuarioServicesImpl.class);

	@Override
	public Empresa addEmpresa(Empresa empresa) throws Exception {
		logger.info("ALTA EMPRESA");
		empresa.setEstado(true);
		Empresa empresaAdd = empresaRepository.save(empresa);
		logger.info("ALTA EXITOSA");
		return empresaAdd;
	}

	@Override
	public Empresa updateEmpresa(Empresa empresa) throws Exception {
		logger.info("MODIFICACIÓN EMPRESA");
		Empresa empresaUpdate = empresaRepository.save(empresa);
		logger.info("MODIFICACIÓN EXITOSA");
		return empresaUpdate;
	}

	@Override
	public Empresa deleteEmpresa(String empresaId) {
		Optional<Empresa> empresa = empresaRepository.findById(empresaId);
		Empresa e = empresa.get();
		e.setEstado(false);
		return empresaRepository.save(e);
	}

	@Override
	public List<Empresa> getEmpresas() {
		List<Empresa> empresas = empresaRepository.findAll();
		return empresas;
	}

	@Override
	public List<Empresa> getEmpresasestado() {
		List<Empresa> empresas = empresaRepository.findByEstado(true);
		return empresas;
	}
	

}
