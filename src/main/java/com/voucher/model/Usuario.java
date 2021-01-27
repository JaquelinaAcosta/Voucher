package com.voucher.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
	
	@Id
	private String _id;
	private String email;
	private String nombre;
	private String apellido;
	private String area;
	private Boolean estado;
	private String password;
	private Empresa empresa;
	private Role role;
	
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String _id, String email, String nombre, String apellido,
			String area, Boolean estado, String password, Empresa empresa, Role role) {
		this._id = _id;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.area = area;
		this.estado = estado;
		this.password = password;
		this.empresa = empresa;
		this.role = role;
	}

	enum Empresa{
		CARSA, EMSA 
	}
	
	enum Role{
		VENTA, ADMIN_EMPRESA, OPERATIVO_EMPRESA, ADMIN_EMSA, ADMIN_GENERICO, VISTA
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
