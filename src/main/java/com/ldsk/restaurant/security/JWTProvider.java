package com.ldsk.restaurant.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTProvider {
	
	private static final int SEGUNDOS = 1000;
	private static final int MINUTOS = 60 * SEGUNDOS;
	private static final int HORAS = 60 * MINUTOS;
	private static final int DIAS = 24 * HORAS;
	private static final int JWT_EXPIRATION = 1 * DIAS;
	
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateToken(Authentication authentication) {
		
		Date expireDate = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
		
		return Jwts.builder()
				.setSubject(authentication.getName())
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String getUsernameFromJWT(String token) {
		
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {

			throw new AuthenticationCredentialsNotFoundException("JWT was expired or is incorrect.");
		}
	}
	
}
