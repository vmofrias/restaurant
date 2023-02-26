package com.ldsk.restaurant.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteDto {

	@JsonProperty(value = "email_owner")
	private String emailOwner;
	
	private String nome;
	
	@JsonProperty(value = "taxa_frete")
	private BigDecimal taxaFrete;

	@JsonProperty(value = "nome_cozinha")
	private String nomeCozinha;
	
}
