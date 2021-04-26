package com.voucher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.google.common.base.Optional;
import com.voucher.model.ERole;
import com.voucher.model.Role;

public interface RoleRepository extends MongoRepository<Role,String>{
	 Role findByName(ERole name);
	 //Role findByRole(ERole name);

}
