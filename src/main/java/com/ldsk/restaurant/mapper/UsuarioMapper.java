package com.ldsk.restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ldsk.restaurant.dto.AuthRegisterRequestDto;
import com.ldsk.restaurant.dto.AuthRegisterResponseDto;
import com.ldsk.restaurant.model.Role;
import com.ldsk.restaurant.model.Usuario;

@Component
public class UsuarioMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario toUsuarioWithEncryptedPassword(AuthRegisterRequestDto authRegisterRequestDto) {
		
		return Usuario.builder()
				.username(authRegisterRequestDto.getUsername())
				.password(passwordEncoder.encode(authRegisterRequestDto.getPassword()))
				.build();
	}
	
	public AuthRegisterResponseDto toAuthRegisterResponseDto(Usuario usuario) {
		
		return AuthRegisterResponseDto.builder()
			.id(usuario.getId())
			.username(usuario.getUsername())
			.roles(usuario.getRoles().stream().map(Role::getName).toList())
			.build();
	}
	
}
