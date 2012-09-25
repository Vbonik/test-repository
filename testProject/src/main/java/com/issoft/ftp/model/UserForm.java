package com.issoft.ftp.model;

import com.issoft.entity.UserRole;
import com.issoft.entity.Users;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 9/19/12
 */

public class UserForm {
    private Users user;
    private int id;
    private List<Users> usersList;
    private List<UserRole> userRoleList;
    private UserRole defaultRole;
    private boolean defaultEnable;

    public UserForm(Users user, int id, List<Users> usersList, List<UserRole> userRoleList) {
        this.user = user;
        this.id = id;
        this.usersList = usersList;
        this.userRoleList = userRoleList;
    }

    public UserForm() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void resetUserAndDefaultRoleEnable() {
//        this.user.resetId();
//        this.user.resetName();
//        this.user.resetPassword();
//        this.user.resetEnabled();
//        this.user.resetUser_roles();
        user.reset();
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

    public void setDefault(Users user) {
        setDefaultEnable(user.isEnabled());
        setDefaultRole(user.getUser_roles());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserForm userForm = (UserForm) o;

        if (id != userForm.id) return false;
        if (user != null ? !user.equals(userForm.user) : userForm.user != null) return false;
        if (userRoleList != null ? !userRoleList.equals(userForm.userRoleList) : userForm.userRoleList != null)
            return false;
        if (usersList != null ? !usersList.equals(userForm.usersList) : userForm.usersList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (usersList != null ? usersList.hashCode() : 0);
        result = 31 * result + (userRoleList != null ? userRoleList.hashCode() : 0);
        return result;
    }
}
