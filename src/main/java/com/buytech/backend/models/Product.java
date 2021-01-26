package com.buytech.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public @Data class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prod")
	private int id_prod;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "sub_brand")
	private String subBrand;
	
	@Column(name = "price")
	private long price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "operating_system")
	private String  operatingSystem;
	
	@Column(name = "processor")
	private String processor;
	
	@Column(name = "graphics")
	private String graphics;
	
	@Column(name = "memory")
	private String memory;
	
	@Column(name = "hard_drive")
	private String hardDrive;
	
	@Column(name = "wireless")
	private String wireless;
	
	@Column(name = "power_supply")
	private String powerSupply;
	
	@Column(name = "battery")
	private String battery;
	
	@Column(name = "screen")
	private int screen;
	
	@Column(name = "quantity")
	private int quantity;

}
