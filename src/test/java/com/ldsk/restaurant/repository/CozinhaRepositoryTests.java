package com.ldsk.restaurant.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.ldsk.restaurant.model.Cozinha;

@DataJpaTest
@ActiveProfiles("testing")
class CozinhaRepositoryTests {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@AfterEach
	void init() {
		
		cozinhaRepository.deleteAll();
	}
	
	@Test
	void itShouldFindByNome() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder()
			.nome("Africana")
			.build();
		
		// Act
		cozinhaRepository.save(cozinha);
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(cozinha.getNome());
		
		// Assert
		Assertions.assertThat(cozinhaOptional).isNotNull();
		Assertions.assertThat(cozinhaOptional.get().getNome()).isEqualTo("Africana");
	}
	
	@Test
	void itShouldNotFindByNome() {
		
		// Arrange
		Cozinha cozinha = Cozinha.builder()
			.nome("Africana")
			.build();
		
		// Act
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(cozinha.getNome());
		
		// Assert
		Assertions.assertThat(cozinhaOptional).isEmpty();
	}
	
}
