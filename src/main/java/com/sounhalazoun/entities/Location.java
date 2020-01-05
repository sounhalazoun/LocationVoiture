package com.sounhalazoun.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Location implements Serializable {

    @EmbeddedId
    private LocationId    id;

    @ManyToOne
    @JoinColumn( name = "client_id", referencedColumnName = "id", insertable = false, updatable = false )
    private Client        client;

    @ManyToOne
    @JoinColumn( name = "voiture_id", referencedColumnName = "id", insertable = false, updatable = false )
    private Voiture       voiture;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime dateDebut;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime dateFin;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime dateDemande;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "manager_id" )
    private Manager       manager;

    private Boolean       status;

    public Location() {

    }

    public Location( Client client, Voiture voiture, LocalDateTime dateDebut, LocalDateTime dateFin,
            LocalDateTime dateDemande, Manager manager,
            Boolean status ) {

        this.id = new LocationId( client.getId(), voiture.getId() );

        this.client = client;
        this.voiture = voiture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateDemande = dateDemande;
        this.manager = manager;
        this.status = status;

        // client.getLocations().add( this );
        // voiture.getLocations().add( this );
    }

    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture( Voiture voiture ) {
        this.voiture = voiture;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus( Boolean status ) {
        this.status = status;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut( LocalDateTime dateDebut ) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin( LocalDateTime dateFin ) {
        this.dateFin = dateFin;
    }

    public LocationId getId() {
        return id;
    }

    public void setId( LocationId id ) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager( Manager manager ) {
        this.manager = manager;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande( LocalDateTime dateDemande ) {
        this.dateDemande = dateDemande;
    }

}
