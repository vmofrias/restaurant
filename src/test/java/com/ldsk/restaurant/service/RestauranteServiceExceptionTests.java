package com.ldsk.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.ldsk.restaurant.exception.RestauranteException;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.repository.RestauranteRepository;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceExceptionTests {
	
	private static final Long RESTAURANTE_ID = 1L;
	
	@Mock
	private RestauranteRepository restauranteRepository;
	
	@InjectMocks
	private RestauranteService restauranteService;
	
	private static Restaurante restaurante;
	
	@BeforeAll
	static void initialArrange() {
		
		// Initial Arrange
		restaurante = Restaurante.builder()
				.id(RESTAURANTE_ID)
				.emailOwner("ldsk@email.com")
				.nome("Restaurante do LDSK")
				.taxaFrete(new BigDecimal("10.0"))
				.cozinha(Mockito.mock(Cozinha.class))
				.build();
	}
	
	@AfterEach
	void init() {
		
		restauranteRepository.deleteAll();
	}
	
	@Test
	void itShouldGetRestauranteByNomeNoSuchElementException() {
		
		// Arrange
		when(restauranteRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(restaurante));
		when(restauranteService.getRestaurantByNome(restaurante)).thenThrow(new NoSuchElementException());
		
		// Act
		RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.getRestaurantByNome(restaurante));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não foi possível encontrar um restaurante com este nome");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findByNome(Mockito.anyString());
	}
	
}
