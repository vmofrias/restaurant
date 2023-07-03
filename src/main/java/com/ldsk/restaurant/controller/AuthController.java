package com.ldsk.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldsk.restaurant.bo.AuthBo;
import com.ldsk.restaurant.dto.AuthLoginRequestDto;
import com.ldsk.restaurant.dto.AuthLoginResponseDto;
import com.ldsk.restaurant.dto.AuthRegisterRequestDto;
import com.ldsk.restaurant.dto.AuthRegisterResponseDto;
import com.ldsk.restaurant.mapper.AuthMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthBo authBo;
	
	@Autowired
	private AuthMapper authMapper;
	
	@PostMapping(value = "/register")
	public ResponseEntity<AuthRegisterResponseDto> registerUser(@RequestBody @Valid AuthRegisterRequestDto authRegisterRequestDto) {
		
		AuthRegisterResponseDto authRegisterResponseDto = authMapper.toAuthRegisterResponseDto(
				authBo.registerUsuario(authMapper.toUsuarioWithEncryptedPassword(authRegisterRequestDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(authRegisterResponseDto);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<AuthLoginResponseDto> loginUser(@RequestBody @Valid AuthLoginRequestDto authLoginRequestDto) {
		
		AuthLoginResponseDto authLoginResponseDto = authMapper.toAuthLoginResponseDto(
				authBo.aunthenticateUsuario(authMapper.toUsuario(authLoginRequestDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(authLoginResponseDto);
	}
	
}
