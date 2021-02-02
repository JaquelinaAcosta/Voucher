package com.voucher.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.voucher.model.Role;
import com.voucher.repository.RoleRepository;
import com.voucher.services.RoleService;

@Service
public class RoleServicesImpl  implements RoleService {
	
	@Inject
	private RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) throws Exception {
		Role roleAdd = roleRepository.save(role);
		return roleAdd;
	}
	
}
