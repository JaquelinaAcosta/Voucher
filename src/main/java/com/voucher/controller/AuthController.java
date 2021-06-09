package com.voucher.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.config.jwt.JwtUtils;
import com.voucher.model.ERole;
import com.voucher.model.Role;
import com.voucher.model.Usuario;
import com.voucher.model.Request.LoginRequest;
import com.voucher.model.Request.SignupRequest;
import com.voucher.model.Response.JwtResponse;
import com.voucher.model.Response.MessageResponse;
import com.voucher.repository.RoleRepository;
import com.voucher.repository.UsuarioRepository;
import com.voucher.services.impl.UserDetailsImpl;
import com.voucher.services.impl.UsuarioServicesImpl;


@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UsuarioRepository  userRepository;
	@Autowired
	RoleRepository roleRepository; 
	@Autowired
	UsuarioServicesImpl usuService;
	
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(new JwtResponse(jwt, 
					 userDetails.getId(),  
					 userDetails.getEmail(), 
					 roles));
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: El Email ya esta registrado!"));
		}
		
		
		if(userRepository.existsByNombre(signUpRequest.getNombre())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: El Usuario ya esta registrado!"));
		}
		
		
		// Create new user's account
		Usuario user = new Usuario();
		user.setApellido(signUpRequest.getApellido());
		user.setEmail(signUpRequest.getEmail());
		//user.setEmpresa(signUpRequest.getEmpresa());
		user.setEstado(signUpRequest.getEstado());
		user.setNombre(signUpRequest.getNombre());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		//user.setTelefono(signUpRequest.getTelefono());
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if(strRoles==null) {
			Role userRole = roleRepository.findByName(ERole.VISTA);
			roles.add(userRole);		
		}
		else {
			strRoles.forEach(rol->{
				switch (rol) {
				case "ADMIN_PARTNER":
					Role role1=roleRepository.findByName(ERole.ADMIN_PARTNER);
					roles.add(role1);
					break;
				case "ROOT":
					Role role2=roleRepository.findByName(ERole.ROOT);
					roles.add(role2);
					break;
				case "ADMIN":
					Role role3=roleRepository.findByName(ERole.ADMIN);
					roles.add(role3);
					break;
				case "VENTA":
					Role role4 = roleRepository.findByName(ERole.VENTA);
					roles.add(role4);
					break;
				case "OPERATIVO_EMPRESA":
					Role role5=roleRepository.findByName(ERole.OPERATIVO_EMPRESA);
					roles.add(role5);
					break;
				case "VISTA":
					Role role6=roleRepository.findByName(ERole.VISTA);
					roles.add(role6);
					break;
				default:
					Role role7=roleRepository.findByName(ERole.VISTA);
					roles.add(role7);
					break;
				}
					});
}
		user.setRoles(roles);
		userRepository.save(user);	
		//usuService.addUsuario(user);
		return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente!"));
		
	}

}
