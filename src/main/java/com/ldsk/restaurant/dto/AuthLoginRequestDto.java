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
public class AuthLoginRequestDto {

	@NotBlank(message = "Campo username é obrigatório")
	private String username;
	
	@NotBlank(message = "Campo password é obrigatório")
	private String password;

}
