package com.ldsk.restaurant.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ldsk.restaurant.security.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private static final String[] AUTH_WHITELIST = {
			"/v3/api-docs/**",
	        "/swagger-ui/**",
	        "/swagger-ui/",
	        "/swagger-ui"};
	
	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";
	
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity http, JWTAuthEntryPoint jwtAuthEntryPoint) throws Exception {
		
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
				.requestMatchers(AUTH_WHITELIST).permitAll()
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/cozinha", "/cozinha/**").hasRole(ADMIN_ROLE)
				.requestMatchers(HttpMethod.GET, "/restaurante").hasAnyRole(ADMIN_ROLE, USER_ROLE)
				.requestMatchers(HttpMethod.POST, "/restaurante/nome").hasAnyRole(ADMIN_ROLE, USER_ROLE)
				.requestMatchers("/restaurante", "/restaurante/**").hasRole(ADMIN_ROLE)
				.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
	}
	
	@Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
		
        return new JWTAuthenticationFilter();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
