package com.ldsk.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinha")
public class CozinhaController {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public ResponseEntity<List<Cozinha>> listAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.getCozinhas());
	}
	
}
