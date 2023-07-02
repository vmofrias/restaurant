package com.ldsk.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {

	@NotBlank(message = "Campo username é obrigatório")
	@Size(max = 100, message = "Limite de caracteres username: 100")
	private String username;
	
	@NotBlank(message = "Campo password é obrigatório")
	@Size(max = 100, message = "Limite de caracteres password: 100")
	private String password;

}
