package com.ldsk.restaurant.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.model.Restaurante;

@DataJpaTest
@ActiveProfiles("testing")
class RestauranteRepositoryTests {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
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
	void itShouldFindByNome() {
		
		// Arrange
		Cozinha savedCozinha = cozinhaRepository.save(Cozinha.builder().nome("Cozinha Caseira da Casa").build());
		
		restaurante.setCozinha(savedCozinha);
		
		// Act
		restauranteRepository.save(restaurante);
		Optional<Restaurante> restauranteOptional = restauranteRepository.findByNome(restaurante.getNome());
		
		// Assert
		Assertions.assertThat(restauranteOptional).isNotNull();
		Assertions.assertThat(restauranteOptional.get().getNome()).isEqualTo("Restaurante do LDSK");
	}
	
	@Test
	void itShouldNotFindByNome() {
		
		// Arrange
		Cozinha savedCozinha = cozinhaRepository.save(Cozinha.builder().nome("Cozinha Caseira da Casa").build());
		
		restaurante.setCozinha(savedCozinha);
		
		// Act
		Optional<Restaurante> restauranteOptional = restauranteRepository.findByNome(restaurante.getNome());
		
		// Assert
		Assertions.assertThat(restauranteOptional).isEmpty();
	}

}
