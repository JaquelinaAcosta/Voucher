package com.voucher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.voucher.model.Usuario;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,String>{

	Optional<Usuario> findById(String id);
	@Query(value = "{'estado' : ?0}")
	List<Usuario> findByEstado(Boolean estado);
	Usuario findByEmail(String email);
	Boolean existsByEmail(String email);
	Boolean existsByNombre(String nombre);
	
	
}
