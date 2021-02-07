package com.buytech.backend.jwt.models;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final Collection<? extends GrantedAuthority> role;

	private final String jwttoken;

}
