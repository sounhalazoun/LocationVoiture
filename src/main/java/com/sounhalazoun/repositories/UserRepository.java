package com.sounhalazoun.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
