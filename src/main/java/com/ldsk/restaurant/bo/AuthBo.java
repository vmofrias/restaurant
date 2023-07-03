package com.ldsk.restaurant.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.model.Usuario;
import com.ldsk.restaurant.service.RoleService;
import com.ldsk.restaurant.service.UsuarioService;

@Service
public class AuthBo {
	
	private static final String REGISTER_DEFAULT_ROLE = "USER";

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	public Usuario registerUsuario(Usuario usuario) {
		
		usuario.setRole(roleService.getRoleByName(REGISTER_DEFAULT_ROLE));
		
		return usuarioService.addUsuario(usuario);
	}
	
}