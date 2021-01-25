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
import com.buytech.backend.models.Client;
import com.buytech.backend.repositories.ClientRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
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
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
		Client client = findClient(id);
		return ResponseEntity.ok(client);
	}
	
	public Client findClient(Integer id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Client not exist with id : " + id));
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
		client.setBirthdate(newClientDetails.getBirthdate());
		
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
