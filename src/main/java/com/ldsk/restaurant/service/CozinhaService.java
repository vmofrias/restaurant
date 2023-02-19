package com.ldsk.restaurant.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha getCozinhaById(long id) {
		
		try {
			
			Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);
			
			if(cozinhaOptional.isPresent()) {
				
				return cozinhaOptional.get();
			} else {
				
				throw new NoSuchElementException();
			}
		} catch (NoSuchElementException e) {
			
			throw new RuntimeException(String.format("Não foi possível encontrar uma cozinha com o id: ", id));
		} catch (Exception e) {

			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	
}
