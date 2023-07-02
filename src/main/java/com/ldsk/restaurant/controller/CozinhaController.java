package com.ldsk.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldsk.restaurant.dto.CozinhaAddRequestDto;
import com.ldsk.restaurant.dto.CozinhaUpdateRequestDto;
import com.ldsk.restaurant.dto.CozinhaDeleteRequestDto;
import com.ldsk.restaurant.dto.CozinhaNameRequestDto;
import com.ldsk.restaurant.dto.CozinhaResponseDto;
import com.ldsk.restaurant.mapper.CozinhaMapper;
import com.ldsk.restaurant.service.CozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cozinha")
public class CozinhaController {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaMapper cozinhaMapper;
	
	@GetMapping
	public ResponseEntity<List<CozinhaResponseDto>> listAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaMapper.toCozinhaListResponseDto(cozinhaService.getCozinhas()));
	}
	
	@PostMapping(value = "/nome")
	public ResponseEntity<CozinhaResponseDto> listByName(@RequestBody @Valid CozinhaNameRequestDto cozinhaNameRequestDto) {
		
		CozinhaResponseDto cozinhaResponseDto = cozinhaMapper.toCozinhaResponseDto(
				cozinhaService.getCozinhaByNome(cozinhaMapper.toCozinha(cozinhaNameRequestDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponseDto);
	}
	
	@PostMapping
	public ResponseEntity<CozinhaResponseDto> addCozinha(@RequestBody @Valid CozinhaAddRequestDto cozinhaAddRequestDto) {
		
		CozinhaResponseDto cozinhaResponseDto = cozinhaMapper.toCozinhaResponseDto(
				cozinhaService.addCozinha(cozinhaMapper.toCozinha(cozinhaAddRequestDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaResponseDto);
	}
	
	@PutMapping
	public ResponseEntity<CozinhaResponseDto> updateCozinha(@RequestBody @Valid CozinhaUpdateRequestDto cozinhaUpdateRequestDto) {
		
		CozinhaResponseDto cozinhaResponseDto = cozinhaMapper.toCozinhaResponseDto(
				cozinhaService.updateCozinha(cozinhaMapper.toCozinha(cozinhaUpdateRequestDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponseDto);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteCozinhaById(@RequestBody @Valid CozinhaDeleteRequestDto cozinhaDeleteRequestDto) {
		
		String cozinhaResponse = cozinhaService
				.deleteCozinhaById(cozinhaMapper.toCozinha(cozinhaDeleteRequestDto));
			
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponse);
	}
	
}
