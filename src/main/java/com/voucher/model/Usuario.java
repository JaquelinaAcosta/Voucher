package com.voucher.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
	
	@Id
	private String _id;
	private String email;
	private String nombre;
	private String apellido;
	private String telefono;
	private Boolean estado;
	private String password;
	@DBRef
	private Empresa empresa;
	@DBRef
	private Set<Role> role;
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String _id, String email, String nombre, String apellido,
			String telefono, Boolean estado, String password, Empresa empresa, Set<Role> role) {
		this._id = _id;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.estado = estado;
		this.password = password;
		this.empresa = empresa;
		this.role = role;
	}
	
	enum Role{
		VENTA, ADMIN, OPERATIVO_EMPRESA, ADMIN_PARTNER, ROOT, VISTA
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Set<Role> getRole() {
		return  role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	
}
