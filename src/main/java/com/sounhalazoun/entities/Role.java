package com.sounhalazoun.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

    @Id
    private String     roles;

    @ManyToMany( mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<User> user;

    public Role() {

    }

    public Role( String roles ) {
        super();
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles( String roles ) {
        this.roles = roles;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser( List<User> user ) {
        this.user = user;
    }

}
