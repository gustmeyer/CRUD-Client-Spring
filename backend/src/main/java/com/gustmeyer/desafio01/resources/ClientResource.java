package com.gustmeyer.desafio01.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustmeyer.desafio01.entity.Client;
import com.gustmeyer.desafio01.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = new ArrayList<>();
		list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<Client>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "8") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "NAME") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			){
			
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<Client> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
		
	}

}
