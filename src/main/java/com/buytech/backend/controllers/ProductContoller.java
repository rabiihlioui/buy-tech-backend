package com.buytech.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buytech.backend.dao.ProductDao;
import com.buytech.backend.exceptions.ResourseNotFoundException;
import com.buytech.backend.models.Product;
import com.buytech.backend.repositories.ProductRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ProductContoller {
	
    private Consumer<Product> productConsumer = (product) -> {
    	updateProduct(product.getId_prod(), product);
    };

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductRepository productRepository;
	
	//get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
    	return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
    
    //create a new product
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
    	System.out.println("*****   " + product);
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
    	product.setProcessorTechnology(newProductDetails.getProcessorTechnology());
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
    
    //update Multiple products
    @PutMapping("/products/updateMultipleProd")
    public ResponseEntity<String> updateMultipleProducts(@RequestBody List<Product> productsList){
    	productsList.forEach(productConsumer);
    	return ResponseEntity.ok("Products updated successfully");
    }
    
    //delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePoduct(@PathVariable Integer id){
    	productRepository.deleteById(id);
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("Product Deleted", Boolean.TRUE);
    	return ResponseEntity.ok(response);
    }
    
	//get products by type
    @GetMapping("/products/productType/{productType}")
    public List<Product> getProductsByType(@PathVariable String productType) {
    	return productDao.getProductsByType(productType);
    }
    
	//get products by screen wide
    @GetMapping("/products/productScreenWide/{screenWide}")
    public List<Product> getProductsScreenWide(@PathVariable int screenWide) {
    	return productDao.getProductsScreenWide(screenWide);
    }
    
	//get products by screen wide
    @GetMapping("/products/price/{minPrice}/{maxPrice}")
    public List<Product> getProductsByPrice(@PathVariable float minPrice, @PathVariable float maxPrice) {
    	return productDao.getProductsByPrice(minPrice, maxPrice);
    }
    
	//get products by manufacturer
    @GetMapping("/products/manufacturer/{manufacturer}")
    public List<Product> getProductsByManufacturer(@PathVariable String manufacturer) {
    	return productDao.getProductsByManufacturer(manufacturer);
    }
    
	//get products by manufacturer
    @GetMapping("/products/sort/{sortType}")
    public List<Product> getProductsBySortType(@PathVariable String sortType) {
    	List<Product> list = productDao.getProductsBySortType(sortType);
//    	Consumer<Product> c = (product) -> System.out.println("the products are : " + product);
//    	list.forEach(c);
    	return list;
    }
    
	//get product types
    @GetMapping("/products/productTypes")
    public List<String> getProductTypesList() {
    	return productDao.getProductTypesList();
    }
	
}
