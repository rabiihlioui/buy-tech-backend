package com.buytech.backend.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class ShoppingId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_client;
	
	private int id_prod;
	
	public ShoppingId(int id_client) {
		this.id_client = id_client;
	}

}
