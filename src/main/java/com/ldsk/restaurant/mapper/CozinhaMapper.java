package com.ldsk.restaurant.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ldsk.restaurant.dto.CozinhaAddRequestDto;
import com.ldsk.restaurant.dto.CozinhaUpdateRequestDto;
import com.ldsk.restaurant.dto.CozinhaDeleteRequestDto;
import com.ldsk.restaurant.dto.CozinhaNameRequestDto;
import com.ldsk.restaurant.dto.CozinhaResponseDto;
import com.ldsk.restaurant.model.Cozinha;

@Component
public class CozinhaMapper {
	
	public Cozinha toCozinha(CozinhaNameRequestDto cozinhaNameRequestDto) {
		
		return Cozinha.builder()
				.nome(cozinhaNameRequestDto.getNome())
				.build();
	}
	
	public Cozinha toCozinha(CozinhaAddRequestDto cozinhaAddRequestDto) {
		
		return Cozinha.builder()
				.id(cozinhaAddRequestDto.getId())
				.nome(cozinhaAddRequestDto.getNome())
				.build();
	}
	
	public Cozinha toCozinha(CozinhaUpdateRequestDto cozinhaUpdateRequestDto) {
		
		return Cozinha.builder()
				.id(cozinhaUpdateRequestDto.getId())
				.nome(cozinhaUpdateRequestDto.getNome())
				.build();
	}
	
	public Cozinha toCozinha(CozinhaDeleteRequestDto cozinhaDeleteRequestDto) {
		
		return Cozinha.builder()
				.id(cozinhaDeleteRequestDto.getId())
				.build();
	}
	
	public CozinhaResponseDto toCozinhaResponseDto(Cozinha cozinha) {
		
		return CozinhaResponseDto.builder()
				.id(cozinha.getId())
				.nome(cozinha.getNome())
				.build();
	}
	
	public List<CozinhaResponseDto> toCozinhaListResponseDto(List<Cozinha> cozinhaList) {
		
		return cozinhaList.stream().map(this::toCozinhaResponseDto).toList();
	}
	
}
