package com.ldsk.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.model.Restaurante;
import com.ldsk.restaurant.repository.RestauranteRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> getRestaurantes() {
		
		return restauranteRepository.findAll();
	}
	
	public Restaurante addRestaurante(Restaurante restaurante) {
		
		try {
			
			Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restaurante.getId());
			
			if(restauranteOptional.isEmpty()) {
				
				return restauranteRepository.save(restaurante);
			} else {
				
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			
			throw new RuntimeException(String.format("Não foi possível adicionar este restaurante pois o id %d já existe.", restaurante.getId()));
		} catch (Exception e) {
			
			throw new RuntimeException(e.getLocalizedMessage());
		}
		
	}
	
}
