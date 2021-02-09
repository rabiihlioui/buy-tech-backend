package com.buytech.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientproduct")
public @Data class Shopping {
	
	@EmbeddedId
	private ShoppingId shoppingId; 
	
	@Column(name = "buying_date")
	private Date buyingDate;
	
	@Column(name = "bought_quantity")
	private int boughtQuantity;

}
