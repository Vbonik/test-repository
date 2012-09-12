/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issoft.ftp.model;

/**
 *
 * @author slavabrodnitski
 */
public class User {

    private Integer id;
    private String username;
    private String password;

    public User() {
        id = -1;
        username = new String("username");// to clean
        password = new String("password");// to clean
    }

    public User(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public User(Integer id, String login, String password) {
        this(login, password);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
