package com.gustmeyer.desafio01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true)
	public Page<Client> findAllPaged(PageRequest pageRequest) {
		Page<Client> obj = repository.findAll(pageRequest);
		return obj;
	}
	
	@Transactional
	public Client add(Client client) {
		repository.save(client);
		
		return client;
	}

	@Transactional
	public Client update(Long id, Client client) {
		Client c = repository.getOne(id);
		
		c.setBirthDate(client.getBirthDate());
		c.setChildren(client.getChildren());
		c.setCpf(client.getCpf());
		c.setIncome(client.getIncome());
		c.setName(client.getName());
		
		c = repository.save(c);
		
		return c;
	}

}
