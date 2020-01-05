package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
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

    @OneToMany( mappedBy = "client", cascade = CascadeType.ALL )
    private List<Location> locations;

    public Client() {

    }

    public Client( String nom, String prenom, String password, String cin, String email, String tel ) {
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations( List<Location> locations ) {
        this.locations = locations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( cin == null ) ? 0 : cin.hashCode() );
        result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( nom == null ) ? 0 : nom.hashCode() );
        result = prime * result + ( ( tel == null ) ? 0 : tel.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Client other = (Client) obj;
        if ( cin == null ) {
            if ( other.cin != null )
                return false;
        } else if ( !cin.equals( other.cin ) )
            return false;
        if ( email == null ) {
            if ( other.email != null )
                return false;
        } else if ( !email.equals( other.email ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        if ( nom == null ) {
            if ( other.nom != null )
                return false;
        } else if ( !nom.equals( other.nom ) )
            return false;
        if ( tel == null ) {
            if ( other.tel != null )
                return false;
        } else if ( !tel.equals( other.tel ) )
            return false;
        return true;
    }

}
