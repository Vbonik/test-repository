package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.UserForm;
import com.issoft.services.Service;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * User: nikitadavydov
 * Date: 9/25/12
 */
public class AdministrationAction extends ActionSupport {
    private static final Logger logger = Logger.getLogger(AdministrationAction.class);

    private UserForm userForm;
    private Service service;

    public String getUserFileList() {
        try {
            userForm.setUsersList(service.getUserList());
        } catch (Exception ex) {
            logger.error(ex);
        }
        return SUCCESS;
    }

    public String addUser() {
        if (userForm.getUser() != null) {
            userForm.resetUserAndDefaultRoleEnable();
        }
        userForm.setUserRoleList(service.getUserRoleList());
        return SUCCESS;
    }

    public String editUser() {
        userForm.setUserRoleList(service.getUserRoleList());
        userForm.setUser(service.getUserById(userForm.getUser_id()));
        userForm.setDefault(userForm.getUser());
        return SUCCESS;
    }

    public String updateUser() {
        service.updateUser(userForm.getUser());
        return SUCCESS;
    }

    public String deleteUser() {
        service.deleteUser(userForm.getUser_id());
        return SUCCESS;
    }


    //gettets and setters
    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
