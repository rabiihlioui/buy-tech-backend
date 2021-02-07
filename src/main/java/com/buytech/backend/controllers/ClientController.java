package com.buytech.backend.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buytech.backend.dao.ClientDao;
import com.buytech.backend.dao.ProductDao;
import com.buytech.backend.exceptions.ResourseNotFoundException;
import com.buytech.backend.models.Client;
import com.buytech.backend.repositories.ClientRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientDao clientDao;
	
	//get all clients
	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	
	//create a new employee
	@PostMapping("/clients")
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	//get client by id
	@GetMapping("/clients/clientById/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		Client client = findClient(id);
		return ResponseEntity.ok(client);
	}

	public Client findClient(Integer id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Client does not exist with id : " + id));
	}

	//get client by email
	@GetMapping("/clients/clientByEmail")
	public ResponseEntity<Client> getClientByEmail(@RequestParam("email") String email) throws ResourseNotFoundException {
		try {
			Client client = clientDao.getClientByEmail(email);
			System.out.println(client);
			return ResponseEntity.ok(client);
		}
		catch(IndexOutOfBoundsException ex) {
			throw new ResourseNotFoundException("Client does not exist with email : " + email);
		}
		
	}

	//update client
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client newClientDetails){
		Client client = findClient(id);
		client.setFirstName(newClientDetails.getFirstName());
		client.setLastName(newClientDetails.getLastName());
		client.setGender(newClientDetails.getGender());
		client.setEmail(newClientDetails.getEmail());
		client.setPassword(newClientDetails.getPassword());
		client.setJoiningDate(newClientDetails.getJoiningDate());
		client.setRole(newClientDetails.getRole());
		
		Client updatedClient = clientRepository.save(client);
		return ResponseEntity.ok(updatedClient);
	}

	//delete client
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Integer id){
		clientRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Client Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
