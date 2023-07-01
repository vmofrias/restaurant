package com.ldsk.restaurant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDeleteRequestDto {

	@NotNull(message = "Campo id é obrigatório")
	private Long id;
	
}
