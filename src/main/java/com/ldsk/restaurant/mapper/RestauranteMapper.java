package com.ldsk.restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ldsk.restaurant.dto.RestauranteDto;
import com.ldsk.restaurant.dto.RestauranteIdDto;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.service.CozinhaService;

@Component
public class RestauranteMapper {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	public Restaurante toRestaurante(RestauranteDto restauranteDto) {
		
		return Restaurante.builder()
				.emailOwner(restauranteDto.getEmailOwner())
				.nome(restauranteDto.getNome())
				.taxaFrete(restauranteDto.getTaxaFrete())
				.cozinha(
						Cozinha
						.builder()
						.nome(restauranteDto.getNome())
						.build()
						)
				.build();
	}
	
	public Restaurante toRestaurante(RestauranteIdDto restauranteIdDto) {
		
		return Restaurante.builder()
				.id(restauranteIdDto.getId())
				.build();
	}

	public Restaurante toRestauranteWithCozinhaSearch(RestauranteDto restauranteDto) {
		
		Cozinha cozinha = cozinhaService.getCozinhaByNome(restauranteDto.getNomeCozinha());
		
		return Restaurante.builder()
				.emailOwner(restauranteDto.getEmailOwner())
				.nome(restauranteDto.getNome())
				.taxaFrete(restauranteDto.getTaxaFrete())
				.cozinha(cozinha)
				.build();
	}
	
	public Restaurante toRestauranteWithCozinhaSearch(RestauranteIdDto restauranteIdDto) {
		
		Cozinha cozinha = cozinhaService.getCozinhaByNome(restauranteIdDto.getNomeCozinha());
		
		return Restaurante.builder()
				.id(restauranteIdDto.getId())
				.emailOwner(restauranteIdDto.getEmailOwner())
				.nome(restauranteIdDto.getNome())
				.taxaFrete(restauranteIdDto.getTaxaFrete())
				.cozinha(cozinha)
				.build();
	}
	
	public RestauranteDto toRestauranteDto(Restaurante restaurante) {
		
		return RestauranteDto
				.builder()
				.emailOwner(restaurante.getEmailOwner())
				.nome(restaurante.getNome())
				.taxaFrete(restaurante.getTaxaFrete())
				.nomeCozinha(restaurante.getCozinha().getNome())
				.build();
	}
	
	public RestauranteIdDto toRestauranteIdDto(Restaurante restaurante) {
		
		return RestauranteIdDto
				.builder()
				.id(restaurante.getId())
				.emailOwner(restaurante.getEmailOwner())
				.nome(restaurante.getNome())
				.taxaFrete(restaurante.getTaxaFrete())
				.nomeCozinha(restaurante.getCozinha().getNome())
				.build();
	}
	
}
