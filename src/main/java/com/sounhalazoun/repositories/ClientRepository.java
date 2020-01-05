package com.sounhalazoun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByEmail( String email );

}
