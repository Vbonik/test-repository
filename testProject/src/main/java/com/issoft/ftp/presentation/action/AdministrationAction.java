package com.issoft.ftp.presentation.action;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;
import com.issoft.ftp.model.AdministrationForm;
import com.issoft.services.Service;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * User: nikitadavydov
 * Date: 9/25/12
 */
public class AdministrationAction extends ActionSupport {
    private static final Logger logger = Logger.getLogger(AdministrationAction.class);

    private AdministrationForm administrationForm;
    private Service service;

    public String getUserFileList() {
        List<UserEntity> allUsers = service.getUserList();

        List<UserEntity> users = new ArrayList<UserEntity>();
        List<UserEntity> newUsers = new ArrayList<UserEntity>();

        for (UserEntity user : allUsers) {
            if (user.checkIsNewUser()) {
                newUsers.add(user);
            } else {
                users.add(user);
            }
        }

        administrationForm.setUsersList(users);
        administrationForm.setNewUsersList(newUsers);
        return SUCCESS;
    }

    public String addUser() {
        if (administrationForm.getUser() != null) {
            administrationForm.resetUserAndDefaultRoleEnable();
        }
        administrationForm.setUserRoleList(service.getUserRoleList());
        return SUCCESS;
    }

    public String editUser() {
        administrationForm.setUserRoleList(service.getUserRoleList());
        administrationForm.setUser(service.getUserById(administrationForm.getUserId()));
        administrationForm.setDefault(administrationForm.getUser());
        return SUCCESS;
    }

    public String updateUser() {
        List<UserRole> roles = service.getUserRoleList();
        for (UserRole userRole : roles) {
            if (userRole.getId() == administrationForm.getUser().getUser_roles().getId()) {
                administrationForm.getUser().setUser_roles(userRole);
                break;
            }
        }
        service.updateUser(administrationForm.getUser());
        return SUCCESS;
    }

    public String deleteUser() {
        service.deleteUser(administrationForm.getUserId());
        return SUCCESS;
    }


    //gettets and setters
    public AdministrationForm getAdministrationForm() {
        return administrationForm;
    }

    public void setAdministrationForm(AdministrationForm administrationForm) {
        this.administrationForm = administrationForm;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
