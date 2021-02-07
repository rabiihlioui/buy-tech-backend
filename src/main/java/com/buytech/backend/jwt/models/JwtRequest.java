package com.buytech.backend.jwt.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private String email;
	private String password;
	
	//need default constructor for JSON Parsing

}
