package com.gustmeyer.desafio01.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustmeyer.desafio01.dtos.ClientDTO;
import com.gustmeyer.desafio01.entity.Client;
import com.gustmeyer.desafio01.repositories.ClientRepository;
import com.gustmeyer.desafio01.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new ClientDTO(client);
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
		return new ClientDTO(c);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO client) {
		try {
			Client c = repository.getOne(id);
			dtoToEntity(c, client);
			c = repository.save(c);
			return new ClientDTO(c);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Element whit id ("+id+") does not exist");
		}
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
