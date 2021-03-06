package com.issoft.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: nikitadavydov
 * Date: 9/17/12
 */
@Entity
@Table(name = "USER_ROLES")
public class UserRole implements Serializable {
    enum Role {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_NEW
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @Column(name = "authority")
    private String authority;

//    you can add this info, if you need
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<User> users = new HashSet<User>();


    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) return false;
        if (authority != null ? !authority.equals(userRole.authority) : userRole.authority != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result =  id ^ (id >>> 32);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}