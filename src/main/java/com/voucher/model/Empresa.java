package com.voucher.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Empresa {
	
	@Id
	private String _id;
	private String empresa;
	private Boolean estado;
	
		
	public Empresa() {
		super();
	}
	
	public Empresa(String empresa) {
		this.empresa = empresa;
	}

	public Empresa(String _id, String empresa) {
		this._id = _id;
		this.empresa = empresa;
	}
	
	public Empresa(String _id, String empresa, Boolean estado) {
		this._id = _id;
		this.empresa = empresa;
		this.estado = estado;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String nombreEmpresa) {
		this.empresa = nombreEmpresa;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

}
