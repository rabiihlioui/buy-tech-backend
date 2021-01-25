package com.buytech.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buytech.backend.exceptions.ResourseNotFoundException;
import com.buytech.backend.models.Product;
import com.buytech.backend.repositories.ProductRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ProductContoller {

	@Autowired
	private ProductRepository productRepository;
	
	//get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
    	return productRepository.findAll();
    }
    
    //create a new product
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
    	return productRepository.save(product);
    }
    
    //get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
    	Product product = findProduct(id);
    	return ResponseEntity.ok(product);
    }
    
    public Product findProduct(Integer id) {
    	return productRepository.findById(id)
    			.orElseThrow(() -> new ResourseNotFoundException("Product not exist with id : " + id));
    }
    
    //update product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProductDetails){
    	Product product = findProduct(id);
    	product.setType(newProductDetails.getType());
    	product.setBrand(newProductDetails.getBrand());
    	product.setSubBrand(newProductDetails.getSubBrand());
    	product.setPrice(newProductDetails.getPrice());
    	product.setDescription(newProductDetails.getDescription());
    	product.setOperatingSystem(newProductDetails.getOperatingSystem());
    	product.setProcessor(newProductDetails.getProcessor());
    	product.setGraphics(newProductDetails.getGraphics());
    	product.setMemory(newProductDetails.getMemory());
    	product.setHardDrive(newProductDetails.getHardDrive());
    	product.setWireless(newProductDetails.getWireless());
    	product.setPowerSupply(newProductDetails.getPowerSupply());
    	product.setBattery(newProductDetails.getBattery());
    	product.setScreen(newProductDetails.getScreen());
    	product.setQuantity(newProductDetails.getQuantity());
    	
    	Product updatedProduct = productRepository.save(product);
    	return ResponseEntity.ok(updatedProduct);
    }
    
    //delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePoduct(@PathVariable Integer id){
    	productRepository.deleteById(id);
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("Product Deleted", Boolean.TRUE);
    	return ResponseEntity.ok(response);
    }
    
	
}