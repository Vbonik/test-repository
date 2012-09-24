package com.issoft.entity;

import javax.persistence.*;

/**
 * User: nikitadavydov
 * Date: 9/17/12
 */
@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole user_roles = new UserRole();

//getters & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void resetId() {
        this.id = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void resetName() {
        this.userName = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void resetPassword() {
        this.password = null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void resetEnabled() {
        this.enabled = false;
    }

    public UserRole getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(UserRole user_roles) {
        this.user_roles = user_roles;
    }

    public void resetUser_roles() {
        this.user_roles = null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (enabled != users.enabled) return false;
        if (id != users.id) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        if (user_roles != null ? !user_roles.equals(users.user_roles) : users.user_roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (user_roles != null ? user_roles.hashCode() : 0);
        return result;
    }
}
