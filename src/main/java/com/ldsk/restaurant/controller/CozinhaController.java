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

import com.ldsk.restaurant.dto.CozinhaDto;
import com.ldsk.restaurant.dto.CozinhaIdDto;
import com.ldsk.restaurant.mapper.CozinhaMapper;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinha")
public class CozinhaController {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaMapper cozinhaMapper;
	
	@GetMapping
	public ResponseEntity<List<Cozinha>> listAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.getCozinhas());
	}
	
	@PostMapping(value = "/nome")
	public ResponseEntity<CozinhaDto> listByName(@RequestBody CozinhaDto cozinhaDto) {
		
		CozinhaDto cozinhaResponseDto = cozinhaMapper.toCozinhaDto(
				cozinhaService.getCozinhaByNome(cozinhaMapper.toCozinha(cozinhaDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponseDto);
	}
	
	@PostMapping
	public ResponseEntity<CozinhaDto> addCozinha(@RequestBody CozinhaDto cozinhaDto) {
		
		CozinhaDto cozinhaResponseDto = cozinhaMapper.toCozinhaDto(
				cozinhaService.addCozinha(cozinhaMapper.toCozinha(cozinhaDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaResponseDto);
	}
	
	@PutMapping
	public ResponseEntity<CozinhaIdDto> updateCozinha(@RequestBody CozinhaIdDto cozinhaIdDto) {
		
		CozinhaIdDto cozinhaResponseIdDto = cozinhaMapper.toCozinhaIdDto(
				cozinhaService.updateCozinha(cozinhaMapper.toCozinha(cozinhaIdDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponseIdDto);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteCozinhaById(@RequestBody CozinhaIdDto cozinhaIdDto) {
		
		String cozinhaResponse = cozinhaService
				.deleteCozinhaById(cozinhaMapper.toCozinha(cozinhaIdDto));
			
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaResponse);
	}
	
	
}
