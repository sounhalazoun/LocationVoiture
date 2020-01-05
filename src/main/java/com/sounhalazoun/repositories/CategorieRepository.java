package com.sounhalazoun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    public Categorie findByNomCat( String cat );

}
