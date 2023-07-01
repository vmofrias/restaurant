package com.ldsk.restaurant.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.CozinhaException;
import com.ldsk.restaurant.model.Cozinha;
import com.ldsk.restaurant.repository.CozinhaRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class CozinhaService {
	
	private static final String ERRO_REQUISICAO = "Erro na requisição";
	private static final String ERRO_SERVIDOR = "Erro no processamento da requisição";
	private static final String COZINHA_DELETED_MESSAGE = "Cozinha com id %d deletada com sucesso!";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> getCozinhas() {
		
		return cozinhaRepository.findAll();
	}
	
	public Cozinha getCozinhaByNome(Cozinha cozinha) {
		
		return getCozinhaByNome(cozinha.getNome());
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
	
	public Cozinha addCozinha(Cozinha cozinha) {
		
		try {
			
			Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinha.getId());
			
			if(cozinhaOptional.isEmpty()) {
				
				return cozinhaRepository.save(cozinha);
			} else {
				
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			
			throw new CozinhaException(String.format("Não foi possível adicionar esta cozinha pois o id %d já existe.", cozinha.getId()),
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			
			throw new CozinhaException(String.format("A cozinha %s já existe.", cozinha.getNome()),
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new CozinhaException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Cozinha updateCozinha(Cozinha cozinha) {
		
		try {
			
			Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinha.getId());
			
			if(cozinhaOptional.isPresent()) {
				
				return cozinhaRepository.save(cozinha);
			} else {
				
				throw new NoSuchElementException();
			}
		} catch (NoSuchElementException e) {
			
			throw new CozinhaException(String.format("Não existe uma cozinha com id %d para ser atualizada.", cozinha.getId()),
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			throw new CozinhaException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	public String deleteCozinhaById(Cozinha cozinha) {
		
		try {
			
			Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinha.getId());
			
			if(cozinhaOptional.isPresent()) {
				
				cozinhaRepository.deleteById(cozinha.getId());
				
				return String.format(COZINHA_DELETED_MESSAGE, cozinha.getId());
			} else {
				
				throw new NoSuchElementException();
			}
		} catch (NoSuchElementException e) {
			
			throw new CozinhaException(String.format("Não existe uma cozinha com id %d para ser deletada.", cozinha.getId()),
					ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			throw new CozinhaException(e.getLocalizedMessage(), ERRO_SERVIDOR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
}
