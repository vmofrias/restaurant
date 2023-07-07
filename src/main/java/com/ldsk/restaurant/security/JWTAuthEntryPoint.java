package com.ldsk.restaurant.security;

import java.io.IOException;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {
	
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String jwtToken = jwtUtil.getJWTFromRequest(request);
		
		if(StringUtils.hasText(jwtToken) && jwtUtil.validateToken(jwtToken)) {
			
			response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
		} else {
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		}
	}

}
