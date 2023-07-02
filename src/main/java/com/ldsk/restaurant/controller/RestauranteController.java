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

import com.ldsk.restaurant.dto.RestauranteAddRequestDto;
import com.ldsk.restaurant.dto.RestauranteDeleteRequestDto;
import com.ldsk.restaurant.dto.RestauranteNameRequestDto;
import com.ldsk.restaurant.dto.RestauranteResponseDto;
import com.ldsk.restaurant.dto.RestauranteUpdateRequestDto;
import com.ldsk.restaurant.mapper.RestauranteMapper;
import com.ldsk.restaurant.service.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteMapper restauranteMapper;
	
	@GetMapping
	public ResponseEntity<List<RestauranteResponseDto>> listAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteMapper.toRestauranteListResponseDto(restauranteService.getRestaurantes()));
	}
	
	@PostMapping(value = "/nome")
	public ResponseEntity<RestauranteResponseDto> listByName(@RequestBody @Valid RestauranteNameRequestDto restauranteNameRequestDto) {
		
		RestauranteResponseDto restauranteResponseDto = restauranteMapper.toRestauranteResponseDto(
				restauranteService.getRestaurantByNome(restauranteMapper.toRestaurante(restauranteNameRequestDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDto);
	}
	
	@PostMapping
	public ResponseEntity<RestauranteResponseDto> addRestaurante(@RequestBody @Valid RestauranteAddRequestDto restauranteAddRequestDto) {
		
		RestauranteResponseDto restauranteResponseDto = restauranteMapper.toRestauranteResponseDto(
				restauranteService.addRestaurante(restauranteMapper.toRestauranteWithCozinhaSearch(restauranteAddRequestDto)));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(restauranteResponseDto);
	}
	
	@PutMapping
	public ResponseEntity<RestauranteResponseDto> updateRestaurante(@RequestBody @Valid RestauranteUpdateRequestDto restauranteUpdateRequestDto) {
		
		RestauranteResponseDto restauranteResponseDto = restauranteMapper.toRestauranteResponseDto(
				restauranteService.updateRestaurante(restauranteMapper.toRestauranteWithCozinhaSearch(restauranteUpdateRequestDto)));
		
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDto);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteRestauranteById(@RequestBody @Valid RestauranteDeleteRequestDto restauranteDeleteRequestDto) {
		
		String restauranteResponse = restauranteService
				.deleteRestauranteById(restauranteMapper.toRestaurante(restauranteDeleteRequestDto));
			
		return ResponseEntity.status(HttpStatus.OK).body(restauranteResponse);
	}
	
}
