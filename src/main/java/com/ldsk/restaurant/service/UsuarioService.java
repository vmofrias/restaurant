package com.ldsk.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.AuthException;
import com.ldsk.restaurant.model.Usuario;
import com.ldsk.restaurant.repository.UsuarioRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class UsuarioService {
	
	private static final String ERRO_REQUISICAO = "Erro na requisição";
	private static final String ERRO_SERVIDOR = "Erro no processamento da requisição";
	private static final String USERNAME_NOT_FOUND_MESSAGE = "User Details não foi encontrado para o usuário: %s";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario getUsuarioByUsername(String username) {
		
		return usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, username)));
	}

	public Usuario addUsuario(Usuario usuario) {
		
		try {
			
			if(!checkIfUsernameExists(usuario.getUsername())) {
				
				return usuarioRepository.save(usuario);
			} else {
				
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			
			throw new AuthException(String.format("O username %s já existe.", usuario.getUsername()), 
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new AuthException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean checkIfUsernameExists(String username) {
		
		return usuarioRepository.existsByUsername(username);
	}
	
}
