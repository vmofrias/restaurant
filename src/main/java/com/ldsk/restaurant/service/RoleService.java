package com.ldsk.restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.AuthException;
import com.ldsk.restaurant.model.Role;
import com.ldsk.restaurant.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {
	
	private static final String ERRO_REQUISICAO = "Erro na requisição";
	private static final String ERRO_SERVIDOR = "Erro no processamento da requisição";
	
	private static final String PREFIX = "ROLE_";
	
	private static final String ROLE_USER = "USER";
	private static final String ROLE_ADMIN = "ADMIN";
	
	@Autowired
	private RoleRepository roleRepository;

	public Role getRoleByName(String name) {
		
		try {
			
			Optional<Role> roleOptional = roleRepository.findByName(name);
			
			if(roleOptional.isPresent()) {
				
				return handleRolesAndAuthorities(roleOptional.get());
			} else {
				
				throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			
			throw new AuthException(String.format("Role %s não existe.", name), 
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new AuthException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Role handleRolesAndAuthorities(Role role) {
		
		if(role.getName().equalsIgnoreCase(ROLE_USER) || role.getName().equalsIgnoreCase(ROLE_ADMIN)) {
			
			role.setName(PREFIX.concat(role.getName()));
			
			return role;
		}
		
		return role;
	}
	
}
