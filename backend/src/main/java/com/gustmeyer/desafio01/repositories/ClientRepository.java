package com.gustmeyer.desafio01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustmeyer.desafio01.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
