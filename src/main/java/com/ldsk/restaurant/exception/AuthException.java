package com.ldsk.restaurant.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String details;
	private HttpStatus httpStatus;
	
	public AuthException(String message, String title) {
		super(message);
		this.title = title;
	}
	
	public AuthException(String message, String title, HttpStatus httpStatus) {
		super(message);
		this.title = title;
		this.httpStatus = httpStatus;
	}
	
	public AuthException(String message, String title, String details, HttpStatus httpStatus) {
		super(message);
		this.title = title;
		this.details = details;
		this.httpStatus = httpStatus;
	}
	
}
