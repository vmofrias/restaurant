package com.ldsk.restaurant.repository;

import java.util.List;
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
	void tearDown() {
		
		cozinhaRepository.deleteAll();
	}
	
	@Test
	void itShouldSaveCozinha() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Cozinha")
			.build();
		
		// when
		Cozinha savedCozinha = cozinhaRepository.save(cozinha);
		
		// then
		Assertions.assertThat(savedCozinha).isNotNull();
		Assertions.assertThat(savedCozinha.getId()).isPositive();
	}

	@Test
	void itShouldUpdateCozinha() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Cozinha")
			.build();
		
		// when
		Cozinha savedCozinha = cozinhaRepository.save(cozinha);
		
		savedCozinha.setNome("kitchen");
		
		Cozinha updatedCozinha = cozinhaRepository.save(savedCozinha);
		
		// then
		Assertions.assertThat(savedCozinha).isNotNull();
		Assertions.assertThat(updatedCozinha).isNotNull();
		Assertions.assertThat(savedCozinha.getId()).isPositive();
		Assertions.assertThat(updatedCozinha.getId()).isPositive();
	}
	
	@Test
	void itShouldDeleteCozinha() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Cozinha")
			.build();
		
		// when
		Cozinha savedCozinha = cozinhaRepository.save(cozinha);
		
		cozinhaRepository.deleteById(savedCozinha.getId());
		Optional<Cozinha> cozinhaReturn = cozinhaRepository.findById(savedCozinha.getId());
		
		// then
		Assertions.assertThat(cozinhaReturn).isEmpty();
	}
	
	@Test
	void itShouldFindByNome() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Africana")
			.build();
		
		// when
		cozinhaRepository.save(cozinha);
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(cozinha.getNome());
		
		// then
		Assertions.assertThat(cozinhaOptional).isNotNull();
		Assertions.assertThat(cozinhaOptional.get().getNome()).isEqualTo("Africana");
	}
	
	@Test
	void itShouldNotFindByNome() {
		
		// given
		Cozinha cozinha = Cozinha.builder()
			.nome("Africana")
			.build();
		
		// when
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(cozinha.getNome());
		
		// then
		Assertions.assertThat(cozinhaOptional).isEmpty();
	}
	
	@Test
	void itShouldReturnListWithTwoCozinhas() {
		
		//given
		Cozinha cozinha1 = Cozinha.builder().nome("Brasileira").build();
		Cozinha cozinha2 = Cozinha.builder().nome("Japonesa").build();
		
		// when
		cozinhaRepository.save(cozinha1);
		cozinhaRepository.save(cozinha2);
		
		List<Cozinha> cozinhaList = cozinhaRepository.findAll();
		
		
		//then
		Assertions.assertThat(cozinhaList)
			.isNotNull()
			.hasSize(2);
	}
	
	@Test
	void itShouldFindCozinhaById() {
		
		// given
		Cozinha cozinha = Cozinha.builder().nome("Brasileira").build();
		
		//when
		Cozinha savedCozinha = cozinhaRepository.save(cozinha);
		Optional<Cozinha> cozinhaReturned = cozinhaRepository.findById(savedCozinha.getId());
		
		// then
		Assertions.assertThat(cozinhaReturned.get()).isNotNull();
	}
	
}
