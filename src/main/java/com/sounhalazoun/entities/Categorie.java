package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categorie implements Serializable {

    @Id
    @GeneratedValue
    private Long   id;
    @NotEmpty
    private String nomCat;
    @NotEmpty
    private String descripCat;

    public String getDescripCat() {
        return descripCat;
    }

    public void setDescripCat( String descripCat ) {
        this.descripCat = descripCat;
    }

    @OneToMany( mappedBy = "categorie", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Voiture> voitures;

    public Categorie() {
        super();
    }

    public Categorie( @NotEmpty String nomCat, List<Voiture> voitures ) {
        super();
        this.nomCat = nomCat;
        this.voitures = voitures;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat( String nomCat ) {
        this.nomCat = nomCat;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures( List<Voiture> voitures ) {
        this.voitures = voitures;
    }

}
