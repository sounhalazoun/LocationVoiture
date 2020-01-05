package com.sounhalazoun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    public Manager findByEmail( String email );

}
