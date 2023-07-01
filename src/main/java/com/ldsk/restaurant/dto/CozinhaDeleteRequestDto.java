package com.ldsk.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CozinhaDeleteRequestDto {

	@NotBlank(message = "Campo id é obrigatório")
	private Long id;
	
}
