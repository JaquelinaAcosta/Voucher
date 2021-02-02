package com.voucher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.voucher.model.Role;

public interface RoleRepository extends MongoRepository<Role,String>{
	
	Role findByRole(String role);

}
