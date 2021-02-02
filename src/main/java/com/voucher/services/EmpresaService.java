package com.voucher.services;

import java.util.List;

import com.voucher.model.Empresa;

public interface EmpresaService {
	
	Empresa addEmpresa(Empresa empresa) throws Exception;
	Empresa updateEmpresa(Empresa empresa) throws Exception;
	Empresa deleteEmpresa(String empresaId);
	List<Empresa> getEmpresas();
	
	List<Empresa> getEmpresasestado();

}
