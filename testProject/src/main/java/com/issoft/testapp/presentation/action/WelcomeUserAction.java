package com.issoft.testapp.presentation.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author slavabrodnitski
 */
public class WelcomeUserAction implements Action{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // all struts logic here
    @Override
    public String execute() {

        return "SUCCESS";

    }
}
