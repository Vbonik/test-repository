package com.issoft.ftp.presentation.action;

import com.issoft.entity.UsersDAO;
import com.issoft.ftp.model.UserForm;
import com.opensymphony.xwork2.ActionSupport;

import static com.issoft.ftp.util.Constants.FAILURE;

/**
 * User: nikitadavydov
 * Date: 9/25/12
 */
public class AdministrationAction extends ActionSupport {
    private UsersDAO usersDAO;
    private UserForm userForm;

    public String getUserFileList() {
        try {
            userForm.setUsersList(usersDAO.list());
        } catch (NullPointerException e) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String addUser() {
        try {
            if (userForm.getUser() != null) {
                userForm.resetUserAndDefaultRoleEnable();
            }
            userForm.setUserRoleList(usersDAO.getUserRoles());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String editUser() {
        try {
            //TODO: getUserRoles - check only one time
            userForm.setUserRoleList(usersDAO.getUserRoles());
            userForm.setUser(usersDAO.getUserById(userForm.getUser_id()));

            userForm.setDefault(userForm.getUser());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String updateUser() {
        try {
            usersDAO.update(userForm.getUser());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }

    public String deleteUser() {
        try {
            usersDAO.delete(userForm.getUser_id());
        } catch (NullPointerException ex) {
            return FAILURE;
        }
        return SUCCESS;
    }


    //gettets and setters
    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }


}
