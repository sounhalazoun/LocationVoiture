package com.sounhalazoun.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    public List<Role> findByRoles( String role );

}
