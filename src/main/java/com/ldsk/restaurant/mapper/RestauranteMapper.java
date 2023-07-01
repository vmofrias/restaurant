package com.ldsk.restaurant.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ldsk.restaurant.dto.RestauranteAddRequestDto;
import com.ldsk.restaurant.dto.RestauranteDeleteRequestDto;
import com.ldsk.restaurant.dto.RestauranteNameRequestDto;
import com.ldsk.restaurant.dto.RestauranteResponseDto;
import com.ldsk.restaurant.dto.RestauranteUpdateRequestDto;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.service.CozinhaService;

@Component
public class RestauranteMapper {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	public Restaurante toRestaurante(RestauranteNameRequestDto restauranteNameRequestDto) {
		
		return Restaurante.builder()
				.nome(restauranteNameRequestDto.getNome())
				.build();
	}
	
	public Restaurante toRestauranteWithCozinhaSearch(RestauranteAddRequestDto restauranteAddRequestDto) {
		
		Cozinha cozinha = cozinhaService.getCozinhaByNome(restauranteAddRequestDto.getNomeCozinha());
		
		return Restaurante.builder()
				.id(restauranteAddRequestDto.getId())
				.emailOwner(restauranteAddRequestDto.getEmailOwner())
				.nome(restauranteAddRequestDto.getNome())
				.taxaFrete(restauranteAddRequestDto.getTaxaFrete())
				.cozinha(cozinha)
				.build();
	}
	
	public Restaurante toRestauranteWithCozinhaSearch(RestauranteUpdateRequestDto restauranteUpdateRequestDto) {
		
		Cozinha cozinha = cozinhaService.getCozinhaByNome(restauranteUpdateRequestDto.getNomeCozinha());
		
		return Restaurante.builder()
				.id(restauranteUpdateRequestDto.getId())
				.emailOwner(restauranteUpdateRequestDto.getEmailOwner())
				.nome(restauranteUpdateRequestDto.getNome())
				.taxaFrete(restauranteUpdateRequestDto.getTaxaFrete())
				.cozinha(cozinha)
				.build();
	}
	
	public Restaurante toRestaurante(RestauranteDeleteRequestDto restauranteDeleteRequestDto) {
		
		return Restaurante.builder()
				.id(restauranteDeleteRequestDto.getId())
				.build();
	}
	
	public RestauranteResponseDto toRestauranteResponseDto(Restaurante restaurante) {
		
		return RestauranteResponseDto.builder()
				.id(restaurante.getId())
				.emailOwner(restaurante.getEmailOwner())
				.nome(restaurante.getNome())
				.taxaFrete(restaurante.getTaxaFrete())
				.nomeCozinha(restaurante.getCozinha().getNome())
				.build();
	}
	
	public List<RestauranteResponseDto> toRestauranteListResponseDto(List<Restaurante> restauranteList) {
		
		return restauranteList.stream().map(this::toRestauranteResponseDto).toList();
	}
	
}
