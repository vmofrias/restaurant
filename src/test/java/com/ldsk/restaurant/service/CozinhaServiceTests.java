package com.ldsk.restaurant.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.repository.CozinhaRepository;

@ExtendWith(MockitoExtension.class)
class CozinhaServiceTests {
	
	@Mock
	private CozinhaRepository cozinhaRepository;

	@InjectMocks
	private CozinhaService cozinhaService;
	
	@Test
	void itShouldGetAllCozinhas() {
		
		List<Cozinha> cozinhas = Arrays.asList(
				Cozinha.builder().nome("cozinha1").build(),
				Cozinha.builder().nome("cozinha2").build());
		
		
		when(cozinhaRepository.findAll()).thenReturn(cozinhas);
		
		List<Cozinha> cozinhaList = cozinhaService.getCozinhas();
	
		Assertions.assertThat(cozinhaList)
			.isNotNull()
			.hasSize(2);
	}
	
	@Test
	void itShouldCreateACozinha() {
		
		Cozinha cozinha = Cozinha.builder().nome("cozinha").build();
		
		when(cozinhaRepository.save(Mockito.any(Cozinha.class))).thenReturn(cozinha);
		
		Cozinha savedCozinha = cozinhaService.addCozinha(cozinha);
	
		Assertions.assertThat(savedCozinha).isNotNull();
		Assertions.assertThat(savedCozinha.getNome()).isEqualTo("cozinha");
	}
	
}
