package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Voiture implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long           id;
    @NotEmpty
    private String         marque;
    @NotEmpty
    private String         moteur;
    @NotNull
    private int            prix;
    @NotNull
    private int            ageMin;
    @NotNull
    private int            permisMin;
    @NotNull
    private int            nbrPassager;
    @NotNull
    private int            nbrPorte;
    @NotNull
    private int            nbrValise;
    @NotEmpty
    private String         boiteVitesse;

    private Boolean        climat;
    private Boolean        gps;

    @NotNull
    private int            franchiseAccident;
    @NotNull
    private int            franchiseVol;
    @NotNull
    private int            caution;
    @NotEmpty
    @Size( max = 350 )
    private String         description;

    private String         image;

    @OneToMany( mappedBy = "voiture", cascade = CascadeType.ALL )
    private List<Location> locations;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "manager_id" )
    private Manager        manager;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "categorie_id" )
    private Categorie      categorie;

    public Voiture() {
        super();
    }

    public Voiture( @NotEmpty String marque, @NotEmpty String moteur, @NotNull int prix, @NotNull int ageMin,
            @NotNull int permisMin, @NotNull int nbrPassager, @NotNull int nbrPorte, @NotNull int nbrValise,
            @NotEmpty String boiteVitesse, Boolean climat, Boolean gps,@NotNull int franchiseAccident,
            @NotNull int franchiseVol, @NotNull int caution, @NotEmpty @Size( max = 350 ) String description,
            String image, Manager manager, Categorie categorie ) {
        super();
        this.marque = marque;
        this.moteur = moteur;
        this.prix = prix;
        this.ageMin = ageMin;
        this.permisMin = permisMin;
        this.nbrPassager = nbrPassager;
        this.nbrPorte = nbrPorte;
        this.nbrValise = nbrValise;
        this.boiteVitesse = boiteVitesse;
        this.climat = climat;
        this.gps=gps;
        this.franchiseAccident = franchiseAccident;
        this.franchiseVol = franchiseVol;
        this.caution = caution;
        this.description = description;
        this.image = image;
        this.manager = manager;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque( String marque ) {
        this.marque = marque;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur( String moteur ) {
        this.moteur = moteur;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix( Integer prix ) {
        this.prix = prix;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin( int ageMin ) {
        this.ageMin = ageMin;
    }

    public int getPermisMin() {
        return permisMin;
    }

    public void setPermisMin( int permisMin ) {
        this.permisMin = permisMin;
    }

    public int getFranchiseAccident() {
        return franchiseAccident;
    }

    public void setFranchiseAccident( int franchiseAccident ) {
        this.franchiseAccident = franchiseAccident;
    }

    public int getFranchiseVol() {
        return franchiseVol;
    }

    public void setFranchiseVol( int franchiseVol ) {
        this.franchiseVol = franchiseVol;
    }

    public int getCaution() {
        return caution;
    }

    public void setCaution( int caution ) {
        this.caution = caution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations( List<Location> locations ) {
        this.locations = locations;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager( Manager manager ) {
        this.manager = manager;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie( Categorie categorie ) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public void setPrix( int prix ) {
        this.prix = prix;
    }

    public int getNbrPassager() {
        return nbrPassager;
    }

    public void setNbrPassager( int nbrPassager ) {
        this.nbrPassager = nbrPassager;
    }

    public int getNbrPorte() {
        return nbrPorte;
    }

    public void setNbrPorte( int nbrPorte ) {
        this.nbrPorte = nbrPorte;
    }

    public int getNbrValise() {
        return nbrValise;
    }

    public void setNbrValise( int nbrValise ) {
        this.nbrValise = nbrValise;
    }

    public String getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse( String boiteVitesse ) {
        this.boiteVitesse = boiteVitesse;
    }

    public Boolean getClimat() {
        return climat;
    }

    public void setClimat( Boolean climat ) {
        this.climat = climat;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps( Boolean gps ) {
        this.gps = gps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( marque == null ) ? 0 : marque.hashCode() );
        result = prime * result + prix;
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
        Voiture other = (Voiture) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        if ( marque == null ) {
            if ( other.marque != null )
                return false;
        } else if ( !marque.equals( other.marque ) )
            return false;
        if ( prix != other.prix )
            return false;
        return true;
    }

}
