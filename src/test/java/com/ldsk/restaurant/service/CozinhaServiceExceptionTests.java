package com.ldsk.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.ldsk.restaurant.exception.CozinhaException;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.repository.CozinhaRepository;

@ExtendWith(MockitoExtension.class)
class CozinhaServiceExceptionTests {
	
	private static final Long COZINHA_ID = 1L;
	
	private static Cozinha cozinha;
	
	@Mock
	private CozinhaRepository cozinhaRepository;

	@InjectMocks
	private CozinhaService cozinhaService;
	
	@BeforeAll
	static void initialArrange() {
		
		// Initial Arrange
		cozinha = Cozinha.builder()
				.id(COZINHA_ID)
				.nome("Japonesa")
				.build();
	}
	
	@AfterEach
	void init() {
		
		cozinhaRepository.deleteAll();
	}
	
	@Test
	void itShouldGetRestauranteByNomeNoSuchElementException() {
		
		// Arrange
		when(cozinhaRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(cozinha));
		when(cozinhaService.getCozinhaByNome(cozinha)).thenThrow(new NoSuchElementException());
		
		// Act
		CozinhaException exception = assertThrows(CozinhaException.class, () -> cozinhaService.getCozinhaByNome(cozinha));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não foi possível encontrar uma cozinha com o nome");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(cozinhaRepository, times(1)).findByNome(Mockito.anyString());
	}
	
}
