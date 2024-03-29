package com.ldsk.restaurant.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.exception.AuthException;
import com.ldsk.restaurant.model.Usuario;
import com.ldsk.restaurant.security.JWTUtil;
import com.ldsk.restaurant.service.RoleService;
import com.ldsk.restaurant.service.UsuarioService;

@Service
public class AuthBo {
	
	private static final String REGISTER_DEFAULT_ROLE = "USER";
	private static final String ERRO_REQUISICAO = "Erro na requisição";

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Usuario registerUsuario(Usuario usuario) {
		
		usuario.setRole(roleService.getRoleByName(REGISTER_DEFAULT_ROLE));
		
		return usuarioService.addUsuario(usuario);
	}
	
	public String aunthenticateUsuario(Usuario usuario) {
		
		Authentication authentication = null;
		
		try {
			
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
		} catch (Exception e) {

			throw new AuthException("Username ou password incorreto.", ERRO_REQUISICAO, HttpStatus.BAD_REQUEST);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return jwtUtil.generateToken(authentication);
	}
	
}
