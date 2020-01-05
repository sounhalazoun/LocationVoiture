package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity

public class User implements Serializable {
    @Id
    private String     email;
    private String     password;
    private int        active;
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable( name = "authorities", joinColumns = { @JoinColumn( name = "email" ) }, inverseJoinColumns = {
            @JoinColumn( name = "roles" ) } )
    private List<Role> role;

    public User() {

    }

    public User( String email, String password, int active, List<Role> role ) {
        super();
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User( String email, String password, int active ) {
        super();
        this.email = email;
        this.password = password;
        this.active = active;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive( int active ) {
        this.active = active;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole( List<Role> role ) {
        this.role = role;
    }

}
