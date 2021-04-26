package com.voucher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.voucher.model.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa,String>{
	
	Optional<Empresa> findById(String id);
	@Query(value = "{'estado' : ?0}")
	List<Empresa> findByEstado(Boolean estado);
}
