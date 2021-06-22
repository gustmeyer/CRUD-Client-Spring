package com.gustmeyer.desafio01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustmeyer.desafio01.entity.Client;
import com.gustmeyer.desafio01.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.get();
	}

}
