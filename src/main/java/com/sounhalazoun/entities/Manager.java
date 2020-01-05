package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
public class Manager implements Serializable {
    @Id
    @GeneratedValue
    private Long           id;
    @NotEmpty
    @Size( min = 3, max = 25 )
    private String         nom;
    @NotEmpty
    @Size( min = 3, max = 25 )
    private String         prenom;
    @NotEmpty
    @Size( min = 6, max = 255 )
    private String         password;
    @NotEmpty
    @Size( min = 8, max = 10 )
    private String         cin;
    @NotEmpty
    @Email
    @NaturalId
    private String         email;
    @NotEmpty
    @Size( max = 10, message = "le numéro du Tel doit être composé par 10 chiffres" )
    private String         tel;

    @OneToMany( mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Voiture>  voitures  = new ArrayList<Voiture>();

    @OneToMany( mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Location> locations = new ArrayList<Location>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations( List<Location> locations ) {
        this.locations = locations;
    }

    public Manager() {
        super();
    }

    public Manager( @NotEmpty @Size( min = 3, max = 25 ) String nom, @NotEmpty @Size( min = 3, max = 25 ) String prenom,
            @NotEmpty @Size( min = 6, max = 255 ) String password, @NotEmpty @Size( min = 8, max = 10 ) String cin,
            @NotEmpty @Email String email,
            @NotEmpty @Size( max = 10, message = "le numéro du Tel doit être composé par 10 chiffres" ) String tel ) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.cin = cin;
        this.email = email;
        this.tel = tel;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin( String cin ) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel( String tel ) {
        this.tel = tel;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures( List<Voiture> voitures ) {
        this.voitures = voitures;
    }

}
