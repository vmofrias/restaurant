package com.ldsk.restaurant.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.CozinhaException;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	private static final String ERRO_REQUISICAO = "Erro na requisição";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> getCozinhas() {
		
		return cozinhaRepository.findAll();
	}

	public Cozinha getCozinhaByNome(String nome) {
		
		try {
			
			Optional<Cozinha> cozinhaOptional = cozinhaRepository.findByNome(nome);
			
			if(cozinhaOptional.isPresent()) {
				
				return cozinhaOptional.get();
			} else {
				
				throw new NoSuchElementException();
			}
		} catch (NoSuchElementException e) {
			
			throw new CozinhaException(String.format("Não foi possível encontrar uma cozinha com o nome: %s", nome), 
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			throw new CozinhaException(e.getLocalizedMessage(), ERRO_REQUISICAO);
		}
	}
	
}
