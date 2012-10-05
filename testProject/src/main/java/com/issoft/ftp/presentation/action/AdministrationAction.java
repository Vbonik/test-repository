package com.issoft.ftp.presentation.action;

import com.issoft.ftp.model.AdministrationForm;
import com.issoft.services.Service;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * User: nikitadavydov
 * Date: 9/25/12
 */
public class AdministrationAction extends ActionSupport {
    private static final Logger logger = Logger.getLogger(AdministrationAction.class);

    private AdministrationForm administrationForm;
    private Service service;

    public String getUserFileList() {
        administrationForm.setUsersList(service.getUserList());
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
        administrationForm.setUser(service.getUserById(administrationForm.getUser_id()));
        administrationForm.setDefault(administrationForm.getUser());
        return SUCCESS;
    }

    public String updateUser() {
        service.updateUser(administrationForm.getUser());
        return SUCCESS;
    }

    public String deleteUser() {
        service.deleteUser(administrationForm.getUser_id());
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
