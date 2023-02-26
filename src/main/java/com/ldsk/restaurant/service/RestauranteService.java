package com.ldsk.restaurant.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.RestauranteException;
import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.repository.RestauranteRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class RestauranteService {
	
	private static final String ERRO_REQUISICAO = "Erro na requisição";
	private static final String ERRO_SERVIDOR = "Erro no processamento da requisição";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> getRestaurantes() {
		
		return restauranteRepository.findAll();
	}
	
	public Restaurante getRestaurantByNome(Restaurante restaurante) {
		
		try {
			
			Optional<Restaurante> restauranteOptional = restauranteRepository.findByNome(restaurante.getNome());
			
			if (restauranteOptional.isPresent()) {
				
				return restauranteOptional.get();
			} else {
				
				throw new NoSuchElementException();
			}
			
		} catch (NoSuchElementException e) {
			
			throw new RestauranteException(String.format("Não foi possível encontrar um restaurante com este nome: %s", restaurante.getNome()), 
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new RestauranteException(e.getLocalizedMessage(), ERRO_REQUISICAO);
		}
	}
	
	public Restaurante addRestaurante(Restaurante restaurante) {
		
		try {
			
			if(restaurante.getId() == null) restaurante.setId(0L); 
				
			Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restaurante.getId());
			
			if(restauranteOptional.isEmpty()) {
				
				return restauranteRepository.save(restaurante);
			} else {
				
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			
			throw new RestauranteException(String.format("Não foi possível adicionar este restaurante pois o id %d já existe.", restaurante.getId()),
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new RestauranteException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
