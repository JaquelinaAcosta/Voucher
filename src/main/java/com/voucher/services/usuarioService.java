package com.voucher.services;

import java.util.List;

import com.voucher.model.Usuario;

public interface UsuarioService {

	Usuario addUsuario(Usuario usuario) throws Exception;
	Usuario updateUsuario(Usuario usuario) throws Exception;
	Usuario deleteUsuario(String usuarioId);
	List<Usuario> getUsuarios();
}
