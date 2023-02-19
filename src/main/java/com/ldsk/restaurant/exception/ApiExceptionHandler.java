package com.ldsk.restaurant.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ldsk.restaurant.vo.ApiExceptionDetailsVo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler {
	
	private static final String REQUISICAO_INCORRETA = "Erro na requisição";
	private static final String REQUISICAO_PROCESSAMENTO_ERRO = "Erro ao processar a requisição. Mensagem: {}";
	
	public ResponseEntity<ApiExceptionDetailsVo> handleRestauranteException(RestauranteException restauranteException) {
		
		log.error(REQUISICAO_PROCESSAMENTO_ERRO, restauranteException.getMessage());
		
		return new ResponseEntity<>(
				ApiExceptionDetailsVo.builder().title(restauranteException.getTitle())
					.httpStatus(restauranteException.getHttpStatus().value())
					.message(restauranteException.getMessage())
					.details(restauranteException.getDetails())
					.timestamp(LocalDateTime.now()).build(),
					restauranteException.getHttpStatus());
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiExceptionDetailsVo> handleInvalidArgument(MethodArgumentNotValidException ex) {
		
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
		
		log.error(REQUISICAO_PROCESSAMENTO_ERRO, String.join("; ", errors));
		
		return new ResponseEntity<>(
				ApiExceptionDetailsVo.builder().title(REQUISICAO_INCORRETA).httpStatus(HttpStatus.BAD_REQUEST.value())
					.message(String.join("; ", errors)).timestamp(LocalDateTime.now()).build(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ApiExceptionDetailsVo> handleConstraintViolation(ConstraintViolationException ex) {
		
		List<ConstraintViolation<?>> constraintViolations = new ArrayList<>();
		constraintViolations.addAll(ex.getConstraintViolations());
		
		List<String> errors = constraintViolations.stream().map(ConstraintViolation::getMessage)
			.collect(Collectors.toList());
		
		log.error(REQUISICAO_PROCESSAMENTO_ERRO, String.join("; ", errors));
		
		return new ResponseEntity<>(
				ApiExceptionDetailsVo.builder().title(REQUISICAO_INCORRETA).httpStatus(HttpStatus.BAD_REQUEST.value())
					.message(String.join("; ", errors)).timestamp(LocalDateTime.now()).build(),
				HttpStatus.BAD_REQUEST);
	}
	
}
