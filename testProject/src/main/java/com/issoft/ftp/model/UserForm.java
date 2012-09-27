package com.issoft.ftp.model;

import com.issoft.entity.User;
import com.issoft.entity.UserRole;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 9/19/12
 */

public class UserForm {
    private User user;
    private String user_id;
    private List<User> usersList;
    private List<UserRole> userRoleList;
    private UserRole defaultRole;
    private boolean defaultEnable;

    public UserForm(com.issoft.entity.User user, String user_id, List<User> usersList, List<UserRole> userRoleList) {
        this.user = user;
        this.user_id = user_id;
        this.usersList = usersList;
        this.userRoleList = userRoleList;
    }

    public UserForm() {
    }

    //getters & setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void resetUserAndDefaultRoleEnable() {
        user.resetUser();
        resetDefaultEnable();
        resetDefaultRole();
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public UserRole getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(UserRole defaultRole) {
        this.defaultRole = defaultRole;
    }

    public void resetDefaultRole() {
        this.defaultRole = null;
    }

    public boolean isDefaultEnable() {
        return defaultEnable;
    }

    public void setDefaultEnable(boolean defaultEnable) {
        this.defaultEnable = defaultEnable;
    }

    public void resetDefaultEnable() {
        this.defaultEnable = false;
    }

    public void setDefault(User user) {
        setDefaultEnable(user.isEnableflag());
        setDefaultRole(user.getUser_roles());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserForm userForm = (UserForm) o;

        if (defaultEnable != userForm.defaultEnable) return false;
        if (defaultRole != null ? !defaultRole.equals(userForm.defaultRole) : userForm.defaultRole != null)
            return false;
        if (user != null ? !user.equals(userForm.user) : userForm.user != null) return false;
        if (userRoleList != null ? !userRoleList.equals(userForm.userRoleList) : userForm.userRoleList != null)
            return false;
        if (user_id != null ? !user_id.equals(userForm.user_id) : userForm.user_id != null) return false;
        if (usersList != null ? !usersList.equals(userForm.usersList) : userForm.usersList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (usersList != null ? usersList.hashCode() : 0);
        result = 31 * result + (userRoleList != null ? userRoleList.hashCode() : 0);
        result = 31 * result + (defaultRole != null ? defaultRole.hashCode() : 0);
        result = 31 * result + (defaultEnable ? 1 : 0);
        return result;
    }
}
