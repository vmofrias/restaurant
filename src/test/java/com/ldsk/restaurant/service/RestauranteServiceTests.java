package com.ldsk.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import com.ldsk.restaurant.exception.RestauranteException;
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
	void whenGetRestauranteByNomeThenReturnSuccess() {
		
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
	void whenGetRestauranteByNomeThenReturnNoSuchElementException() {
		
		// Arrange
		when(restauranteRepository.findByNome(Mockito.anyString())).thenReturn(Optional.empty());
		
		// Act
		RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.getRestaurantByNome(restaurante));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não foi possível encontrar um restaurante com este nome:");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findByNome(Mockito.anyString());	
	}
	
	@Test
	void whenAddRestauranteThenReturnSuccess() {
		
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
	void whenAddRestauranteThenReturnEntityExistsException() {
		
		// Arrange
		when(restauranteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurante));
		
		// Act
		RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.addRestaurante(restaurante));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não foi possível adicionar este restaurante pois o id");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findById(Mockito.anyLong());	
	}
	
	@Test
	void whenAddRestauranteThenReturnDataIntegrityViolationException() {
		
		// Arrange
		when(restauranteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		when(restauranteRepository.save(restaurante)).thenThrow(DataIntegrityViolationException.class);
		
		// Act
		RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.addRestaurante(restaurante));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("O restaurante com o nome");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findById(Mockito.anyLong());	
		verify(restauranteRepository, times(1)).save(restaurante);
	}
	
	@Test
	void whenUpdateRestauranteThenReturnSuccess() {
		
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
	void whenUpdateRestauranteThenReturnNoSuchElementException() {
		
		// Arrange
		when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.empty());
		
		// Act
		RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.updateRestaurante(restaurante));
		
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não existe um restaurante com id");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findById(Mockito.anyLong());	
	}
	
	@Test
	void whenDeleteRestauranteThenReturnSuccess() {
		
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
	
	@Test
	void whenDeleteRestauranteThenReturnNoSuchElementException() {
		
		// Arrange
		when(restauranteRepository.findById(restaurante.getId())).thenReturn(Optional.empty());
		
		// Act
        RestauranteException exception = assertThrows(RestauranteException.class, () -> restauranteService.deleteRestauranteById(restaurante));

        // Assert
		// Assert
		Assertions.assertThat(exception.getMessage()).isNotBlank();
		Assertions.assertThat(exception.getMessage()).contains("Não existe um restaurante com id");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(restauranteRepository, times(1)).findById(Mockito.anyLong());
	}
	
	
}
