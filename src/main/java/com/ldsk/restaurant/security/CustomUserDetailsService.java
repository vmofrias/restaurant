package com.ldsk.restaurant.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ldsk.restaurant.model.Role;
import com.ldsk.restaurant.model.Usuario;
import com.ldsk.restaurant.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final String PREFIX_ROLE = "ROLE_";
	
	private static final String ROLE_USER = "USER";
	private static final String ROLE_ADMIN = "ADMIN";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.getUsuarioByUsername(username);
		
		authorityToRoleConverter(usuario);
		
		return new User(usuario.getUsername(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	private void authorityToRoleConverter(Usuario usuario) {
		
		for(Role role : usuario.getRoles()) {
			
			if(role.getName().equalsIgnoreCase(ROLE_USER) || role.getName().equalsIgnoreCase(ROLE_ADMIN)) {
				
				role.setName(PREFIX_ROLE.concat(role.getName()));
			}
		}
	}
	
}
