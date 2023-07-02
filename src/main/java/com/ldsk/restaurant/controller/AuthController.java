package com.ldsk.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldsk.restaurant.bo.AuthBo;
import com.ldsk.restaurant.config.JWTProvider;
import com.ldsk.restaurant.dto.AuthLoginRequestDto;
import com.ldsk.restaurant.dto.AuthLoginResponseDto;
import com.ldsk.restaurant.dto.AuthRegisterRequestDto;
import com.ldsk.restaurant.dto.AuthRegisterResponseDto;
import com.ldsk.restaurant.mapper.UsuarioMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthBo authBo;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	@PostMapping(value = "/register")
	public ResponseEntity<AuthRegisterResponseDto> registerUser(@RequestBody @Valid AuthRegisterRequestDto authRegisterRequestDto) {
		
		AuthRegisterResponseDto authRegisterResponseDto = usuarioMapper.toAuthRegisterResponseDto(
				authBo.registerUsuario(usuarioMapper.toUsuarioWithEncryptedPassword(authRegisterRequestDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(authRegisterResponseDto);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<AuthLoginResponseDto> loginUser(@RequestBody @Valid AuthLoginRequestDto authLoginRequestDto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authLoginRequestDto.getUsername(), authLoginRequestDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		return ResponseEntity.status(HttpStatus.OK).body(AuthLoginResponseDto.builder().accessToken(token).build());
	}
	
}
