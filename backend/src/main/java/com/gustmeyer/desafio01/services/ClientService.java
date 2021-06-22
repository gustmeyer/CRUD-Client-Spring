package com.gustmeyer.desafio01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	public List<Client> findAll() {
		List<Client> obj = repository.findAll();
		return obj;
	}
	
	public Page<Client> findAllPaged(PageRequest pageRequest) {
		Page<Client> obj = repository.findAll(pageRequest);
		return obj;
	}

}
