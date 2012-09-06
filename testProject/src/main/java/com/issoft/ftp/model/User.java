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
    private String login;
    private String password;

    public User() {
        id = -1;
        login = new String();
        password = new String();
    }
    public User(String login, String password) {
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
