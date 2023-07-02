package com.ldsk.restaurant.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterResponseDto {

	private Long id;
	
	private String username;

	private List<String> roles;
	
}
