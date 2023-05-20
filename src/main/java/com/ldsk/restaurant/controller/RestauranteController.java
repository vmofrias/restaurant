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

import com.ldsk.restaurant.dto.RestauranteDto;
import com.ldsk.restaurant.dto.RestauranteIdDto;
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
	public ResponseEntity<List<Restaurante>> listAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteService.getRestaurantes());
	}
	
	@PostMapping(value = "/nome")
	public ResponseEntity<RestauranteIdDto> listByName(@RequestBody RestauranteDto restauranteDto) {
		
		RestauranteIdDto restauranteResponseIdDto = restauranteMapper.toRestauranteIdDto(
				restauranteService.getRestaurantByNome(restauranteMapper.toRestaurante(restauranteDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseIdDto);
	}
	
	@PostMapping
	public ResponseEntity<RestauranteIdDto> addRestaurante(@RequestBody RestauranteDto restauranteDto) {
		
		RestauranteIdDto restauranteResponseIdDto = restauranteMapper.toRestauranteIdDto(
				restauranteService.addRestaurante(restauranteMapper.toRestauranteWithCozinhaSearch(restauranteDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(restauranteResponseIdDto);
	}
	
	@PutMapping
	public ResponseEntity<RestauranteIdDto> updateRestaurante(@RequestBody RestauranteIdDto restauranteIdDto) {
		
		RestauranteIdDto restauranteResponseIdDto = restauranteMapper.toRestauranteIdDto(
				restauranteService.updateRestaurante(restauranteMapper.toRestauranteWithCozinhaSearch(restauranteIdDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseIdDto);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteRestauranteById(@RequestBody RestauranteIdDto restauranteIdDto) {
		
		String restauranteResponse = restauranteService
				.deleteRestauranteById(restauranteMapper.toRestaurante(restauranteIdDto));
			
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponse);
	}
	
}
