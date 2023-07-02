package com.ldsk.restaurant.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String details;
	private HttpStatus httpStatus;
	
	public UsuarioException(String message, String title) {
		super(message);
		this.title = title;
	}
	
	public UsuarioException(String message, String title, HttpStatus httpStatus) {
		super(message);
		this.title = title;
		this.httpStatus = httpStatus;
	}
	
	public UsuarioException(String message, String title, String details, HttpStatus httpStatus) {
		super(message);
		this.title = title;
		this.details = details;
		this.httpStatus = httpStatus;
	}
	
}
