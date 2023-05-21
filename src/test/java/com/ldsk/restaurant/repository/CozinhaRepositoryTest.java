package com.ldsk.restaurant.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ldsk.restaurant.model.Cozinha;

@DataJpaTest
class CozinhaRepositoryTest {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Test
	void itShouldFindByNome() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Africana")
			.build();
		
		cozinhaRepository.save(cozinha);
		
		// when
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(cozinha.getNome());
		
		// then
		assertThat(cozinhaOptional.get().getNome()).isEqualTo("Africana");
		
	}
	
}
