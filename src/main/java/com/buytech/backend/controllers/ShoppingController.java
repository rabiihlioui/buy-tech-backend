package com.buytech.backend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buytech.backend.dao.ClientDao;
import com.buytech.backend.dao.ShoppingDao;
import com.buytech.backend.models.Shopping;
import com.buytech.backend.models.ShoppingId;
import com.buytech.backend.repositories.ShoppingRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ShoppingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);
	
	@Autowired
	private ShoppingRepository shoppingRepository;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ShoppingDao shoppingDao;
	
	//get all bought product
	@GetMapping("/boughtProducts")
	public List<Shopping> getAllBoughtProducts() {
		return shoppingRepository.findAll();
	}

	//buy a product
	@PostMapping("/boughtProducts/buyProduct")
	public Shopping buyProduct(@RequestBody Shopping shopping, @RequestParam("email") String email) {
		int clientId = clientDao.getClientByEmail(email).getId_client();
		ShoppingId shoppingId = new ShoppingId(clientId, shopping.getShoppingId().getId_prod());
		shopping.setShoppingId(shoppingId);
		return shoppingRepository.save(shopping);
	}
	
	//buy products
	@PostMapping("/boughtProducts/buyMultipleProducts")
	public List<Shopping> buyMultipleProducts(@RequestBody List<Shopping> shoppingList, @RequestParam("email") String email) {
		System.out.println(shoppingList);
		List<Shopping> shoppingListResponse = new ArrayList<Shopping>();
		for (Shopping shopping : shoppingList) {
			shoppingListResponse.add(shoppingRepository.save(shopping));
		}
		return shoppingListResponse;
	}

	//delete bought product
	@DeleteMapping("/boughtProducts/deleteBoughtProduct")
	public ResponseEntity<Map<String, Boolean>> deleteBoughtProduct(@RequestParam("clientId") String clientId, @RequestParam("prodId") String prodId){
		ShoppingId sId = new ShoppingId(Integer.valueOf(clientId), Integer.valueOf(prodId));
		shoppingRepository.deleteById(sId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Bought Product Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	//delete all bought products for one client
	@DeleteMapping("/boughtProducts/deleteAllBoughtProductsByClient")
	public ResponseEntity<Map<String, Boolean>> deleteAllBoughtProductsByClient(@RequestParam("clientId") String clientId){
		String message = shoppingDao.deleteAllBoughtProductsByClient(Integer.valueOf(clientId));
		Map<String, Boolean> response = new HashMap<>();
		response.put(message, Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	//delete all bought products
	@DeleteMapping("/boughtProducts/deleteAllBoughtProducts")
	public ResponseEntity<Map<String, Boolean>> deleteAllBoughtProducts(){
		shoppingRepository.deleteAll();
		Map<String, Boolean> response = new HashMap<>();
		response.put("All Bought Products Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
