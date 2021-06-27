package com.gustmeyer.desafio01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustmeyer.desafio01.dtos.ClientDTO;
import com.gustmeyer.desafio01.entity.Client;
import com.gustmeyer.desafio01.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		ClientDTO client = new ClientDTO(obj.get());
		return client;
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> obj = repository.findAll(pageRequest);
		
		return obj.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO add(ClientDTO client) {
		Client c = new Client();
		dtoToEntity(c, client);
		repository.save(c);
		return client;
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO client) {
		Client c = repository.getOne(id);
		
		dtoToEntity(c, client);
		
		c = repository.save(c);
		return client;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Client dtoToEntity(Client client, ClientDTO dto) {
		
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setName(dto.getName());
		
		return client;
		
	}
	
}
