package com.ldsk.restaurant.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteAddRequestDto {

	@NotBlank(message = "Campo email_owner é obrigatório")
	@JsonProperty(value = "email_owner")
	private String emailOwner;
	
	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "Campo taxa_frete é obrigatório")
	@JsonProperty(value = "taxa_frete")
	private BigDecimal taxaFrete;

	@NotBlank(message = "Campo nome_cozinha é obrigatório")
	@JsonProperty(value = "nome_cozinha")
	private String nomeCozinha;
	
}
