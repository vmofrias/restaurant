package com.ldsk.restaurant.mapper;

import org.springframework.stereotype.Component;

import com.ldsk.restaurant.dto.CozinhaDto;
import com.ldsk.restaurant.dto.CozinhaIdDto;
import com.ldsk.restaurant.dto.RestauranteIdDto;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;

@Component
public class CozinhaMapper {
	
	public Cozinha toCozinha(CozinhaDto cozinhaDto) {
		
		return Cozinha.builder()
				.nome(cozinhaDto.getNome())
				.build();
	}
	
	public Cozinha toCozinha(CozinhaIdDto cozinhaIdDto) {
		
		return Cozinha.builder()
				.id(cozinhaIdDto.getId())
				.nome(cozinhaIdDto.getNome())
				.build();
	}
	
	public Restaurante toRestaurante(RestauranteIdDto restauranteIdDto) {
		
		return Restaurante.builder()
				.id(restauranteIdDto.getId())
				.build();
	}

	public CozinhaDto toCozinhaDto(Cozinha cozinha) {
		
		return CozinhaDto
				.builder()
				.nome(cozinha.getNome())
				.build();
	}
	
	public CozinhaIdDto toCozinhaIdDto(Cozinha cozinha) {
		
		return CozinhaIdDto
				.builder()
				.id(cozinha.getId())
				.nome(cozinha.getNome())
				.build();
	}
	
}
