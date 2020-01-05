package com.sounhalazoun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sounhalazoun.entities.Categorie;
import com.sounhalazoun.entities.Manager;
import com.sounhalazoun.entities.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    public List<Voiture> findByManager( Manager manager );

    public List<Voiture> findByCategorie( Categorie categorie );

}
