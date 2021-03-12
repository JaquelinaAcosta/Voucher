package com.voucher.services;

import java.util.List;

import com.voucher.model.Role;
import com.voucher.model.Usuario;

public interface RoleService {
	
	Role addRole(Role role) throws Exception;
	List<Role> getRoles();

}
