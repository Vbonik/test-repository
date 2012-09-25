package com.issoft.entity;

import javax.persistence.*;

/**
 * User: nikitadavydov
 * Date: 9/17/12
 */
@Entity
@Table(name = "USER_ROLES")
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "authority")
    private String authority;

//    you can add this info, if you need
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Users> users = new HashSet<Users>();

    //getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}