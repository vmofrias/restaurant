package com.ldsk.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldsk.restaurant.dto.RestauranteDto;
import com.ldsk.restaurant.mapper.RestauranteMapper;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteMapper restauranteMapper;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteService.getRestaurantes());
	}
	
	@PostMapping
	public ResponseEntity<RestauranteDto> adicionarRestaurante(@RequestBody RestauranteDto restauranteDto) {
		
		RestauranteDto restauranteResponseDto = restauranteMapper.toRestauranteDto(
				restauranteService.addRestaurante(restauranteMapper.toRestaurante(restauranteDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDto);
	}
	
}
