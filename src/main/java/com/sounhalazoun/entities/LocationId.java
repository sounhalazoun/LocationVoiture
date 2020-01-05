package com.sounhalazoun.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationId implements Serializable {

    @Column( name = "client_id" )
    private Long client;

    @Column( name = "voiture_id" )
    private Long voiture;

    public LocationId() {

    }

    public LocationId( Long client, Long voiture ) {

        this.client = client;
        this.voiture = voiture;
    }

    public Long getClient() {
        return client;
    }

    public Long getVoiture() {
        return voiture;
    }

    public void setClient( Long client ) {
        this.client = client;
    }

    public void setVoiture( Long voiture ) {
        this.voiture = voiture;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( client == null ) ? 0 : client.hashCode() );
        result = prime * result + ( ( voiture == null ) ? 0 : voiture.hashCode() );
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
        LocationId other = (LocationId) obj;
        if ( client == null ) {
            if ( other.client != null )
                return false;
        } else if ( !client.equals( other.client ) )
            return false;
        if ( voiture == null ) {
            if ( other.voiture != null )
                return false;
        } else if ( !voiture.equals( other.voiture ) )
            return false;
        return true;
    }

}
