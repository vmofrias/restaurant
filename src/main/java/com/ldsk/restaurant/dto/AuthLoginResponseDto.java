package com.ldsk.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponseDto {

	@JsonProperty(value = "access_token")
	private String accessToken;
	
	@Builder.Default
	@JsonProperty(value = "token_type")
    private String tokenType = "Bearer ";
	
}
