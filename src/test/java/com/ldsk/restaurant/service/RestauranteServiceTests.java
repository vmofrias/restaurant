package com.ldsk.restaurant.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.repository.RestauranteRepository;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTests {
	
	private static final Long RESTAURANTE_ID = 1L;
	
	private static Restaurante restaurante;

	@Mock
	private RestauranteRepository restauranteRepository;
	
	@InjectMocks
	private RestauranteService restauranteService;
	
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
	void itShouldGetRestauranteByNome() {
		
		// Arrange
		when(restauranteRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(restaurante));
		
		// Act
		Restaurante restauranteReturn = restauranteService.getRestaurantByNome(restaurante);
		
		// Assert
		Assertions.assertThat(restauranteReturn).isNotNull();
		Assertions.assertThat(restauranteReturn.getNome()).isEqualTo(restaurante.getNome());
		
		verify(restauranteRepository, times(1)).findByNome(Mockito.anyString());
	}
	
	@Test
	void itShouldAddRestaurante() {
		
		// Arrange
		when(restauranteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		when(restauranteRepository.save(Mockito.any(Restaurante.class))).thenReturn(restaurante);
		
		// Act
		Restaurante savedRestaurante = restauranteService.addRestaurante(restaurante);
		
		// Assert
		Assertions.assertThat(savedRestaurante).isNotNull();
		Assertions.assertThat(savedRestaurante.getNome()).isEqualTo(restaurante.getNome());
		
		verify(restauranteRepository, times(1)).findById(Mockito.anyLong());
		verify(restauranteRepository, times(1)).save(Mockito.any(Restaurante.class));
	}
	
	@Test
	void itShouldUpdateRestaurante() {
		
		// Arrange
		when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.of(restaurante));
		when(restauranteRepository.save(Mockito.any(Restaurante.class))).thenReturn(restaurante);
		
		// Act
		Restaurante updatedRestaurante = restauranteService.updateRestaurante(restaurante);
		
		// Assert
		Assertions.assertThat(updatedRestaurante)
			.isNotNull()
			.isEqualTo(restaurante);
		
		verify(restauranteRepository, times(1)).findById(restaurante.getId());
        verify(restauranteRepository, times(1)).save(Mockito.any(Restaurante.class));
	}
	
	@Test
	void itShouldDeleteRestaurante() {
		
		// Arrange
		when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.of(restaurante));
		
		// Act
        String result = restauranteService.deleteRestauranteById(restaurante);

        // Assert
        Assertions.assertThat(result)
        	.isNotEmpty()
        	.contains(String.valueOf(restaurante.getId()));
        
        verify(restauranteRepository, times(1)).findById(restaurante.getId());
        verify(restauranteRepository, times(1)).deleteById(restaurante.getId());
	}
	
	
}
