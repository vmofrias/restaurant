package com.ldsk.restaurant.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
	
	@AfterEach
	void init() {
		
		cozinhaRepository.deleteAll();
	}
	
	@Test
	void itShouldGetAllCozinhas() {
		
		// Arrange
		List<Cozinha> cozinhas = Arrays.asList(
				Cozinha.builder().nome("cozinha1").build(),
				Cozinha.builder().nome("cozinha2").build());
		
		
		when(cozinhaRepository.findAll()).thenReturn(cozinhas);
		
		// Act
		List<Cozinha> cozinhaList = cozinhaService.getCozinhas();
	
		// Assert
		Assertions.assertThat(cozinhaList)
			.isNotNull()
			.hasSize(2);
		
		verify(cozinhaRepository, times(1)).findAll();
	}
	
	@Test
	void itShouldGetCozinhaByNome() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder().nome("cozinha").build();
		
		when(cozinhaRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(cozinha));
		
		// Act
		Cozinha cozinhaReturn = cozinhaService.getCozinhaByNome(cozinha);
	
		// Assert
		Assertions.assertThat(cozinhaReturn).isNotNull();
		Assertions.assertThat(cozinhaReturn.getNome()).isEqualTo(cozinha.getNome());
		
		verify(cozinhaRepository, times(1)).findByNome(Mockito.anyString());
	}
	
	@Test
	void itShouldAddCozinha() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder().id(0L).nome("cozinha").build();
		
		when(cozinhaRepository.findById(cozinha.getId())).thenReturn(Optional.empty());
		when(cozinhaRepository.save(Mockito.any(Cozinha.class))).thenReturn(cozinha);
		
		// Act
		Cozinha savedCozinha = cozinhaService.addCozinha(cozinha);
	
		// Assert
		Assertions.assertThat(savedCozinha).isNotNull();
		Assertions.assertThat(savedCozinha.getNome()).isEqualTo("cozinha");
		
		verify(cozinhaRepository, times(1)).findById(cozinha.getId());
		verify(cozinhaRepository, times(1)).save(Mockito.any(Cozinha.class));
	}
	
	@Test
	void itShouldUpdateCozinha() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder().id(1L).nome("cozinha").build();
		
		when(cozinhaRepository.findById(cozinha.getId())).thenReturn(Optional.of(cozinha));
		when(cozinhaRepository.save(Mockito.any(Cozinha.class))).thenReturn(cozinha);
		
		// Act
		Cozinha updatedCozinha = cozinhaService.updateCozinha(cozinha);
	
		// Assert
		Assertions.assertThat(updatedCozinha)
			.isNotNull()
			.isEqualTo(cozinha);
		
		verify(cozinhaRepository, times(1)).findById(cozinha.getId());
        verify(cozinhaRepository, times(1)).save(Mockito.any(Cozinha.class));
	}
	
	@Test
	void itShouldDeleteCozinha() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder().id(1L).nome("cozinha").build();
		
		when(cozinhaRepository.findById(cozinha.getId())).thenReturn(Optional.of(cozinha));
		
		// Act
        String result = cozinhaService.deleteCozinhaById(cozinha);

        // Assert
        Assertions.assertThat(result).isNotEmpty();
        
        verify(cozinhaRepository, times(1)).findById(cozinha.getId());
        verify(cozinhaRepository, times(1)).deleteById(cozinha.getId());
	}
	
}
