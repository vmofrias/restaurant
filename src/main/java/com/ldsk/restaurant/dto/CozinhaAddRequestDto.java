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
public class CozinhaAddRequestDto {

	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;
	
}
