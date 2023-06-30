package com.ldsk.restaurant.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.repository.CozinhaRepository;
import com.ldsk.restaurant.repository.RestauranteRepository;

@ExtendWith(MockitoExtension.class)
public class RestauranteServiceTests {

	@Mock
	private RestauranteRepository restauranteRepository;
	
	@Mock
	private CozinhaRepository cozinhaRepository;
	
	@InjectMocks
	private RestauranteService restauranteService;
	
	private Restaurante restaurante;
	
	@BeforeEach
	void beforeInit() {
		
		restaurante = Restaurante.builder()
				.emailOwner("ldsk@email.com")
				.nome("Restaurante do LDSK")
				.taxaFrete(new BigDecimal("10.0"))
				.build();
	}
	
	@AfterEach
	void init() {
		
		restauranteRepository.deleteAll();
	}
	
	
	@Test
	void itShouldGetRestauranteByNome() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder().nome("Cozinha Caseira da Casa").build();
		
		when(cozinhaRepository.save(Mockito.any(Cozinha.class))).thenReturn(cozinha);
		
		Cozinha savedCozinha = cozinhaRepository.save(cozinha);
		
		restaurante.setCozinha(savedCozinha);
		
		when(restauranteRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(restaurante));
		
		// Act
		Restaurante restauranteReturn = restauranteService.getRestaurantByNome(restaurante);
		
		// Assert
		Assertions.assertThat(restauranteReturn).isNotNull();
		Assertions.assertThat(restauranteReturn.getNome()).isEqualTo(restaurante.getNome());
		
		verify(cozinhaRepository, times(1)).save(Mockito.any(Cozinha.class));
		verify(restauranteRepository, times(1)).findByNome(Mockito.anyString());
	}
}
