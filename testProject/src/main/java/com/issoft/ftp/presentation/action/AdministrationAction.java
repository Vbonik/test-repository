package com.issoft.ftp.presentation.action;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;
import com.issoft.ftp.model.AdministrationForm;
import com.issoft.ftp.util.Constants;
import com.issoft.ftp.util.ConvertUtil;
import com.issoft.services.Service;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: nikitadavydov
 * Date: 9/25/12
 */
public class AdministrationAction extends ActionSupport {
    private static final Logger LOGGER = Logger.getLogger(AdministrationAction.class);

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
        administrationForm.setRestrictEdit(false);
        return SUCCESS;
    }

    public String editUser() {
        administrationForm.setUserRoleList(service.getUserRoleList());
        administrationForm.setUser(service.getUserById(administrationForm.getUserId()));
        administrationForm.setDefault(administrationForm.getUser());
        administrationForm.setRestrictEdit(true);
        return SUCCESS;
    }

    /**
     * Updates user password - convert to md5
     * @param originalPassword Original user password
     * @return <code>true</code> - in case of success convert, <code>false</code> - otherwise
     */
    private boolean convertUserPassword(String originalPassword) {
        try {
            String md5Password = ConvertUtil.stringToMD5(originalPassword);
            administrationForm.getUser().setUserPassword(md5Password);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    public String updateUser() {
        List<UserRole> roles = service.getUserRoleList();
        for (UserRole userRole : roles) {
            if (userRole.getId() == administrationForm.getUser().getUser_roles().getId()) {
                administrationForm.getUser().setUser_roles(userRole);
                break;
            }
        }
        if (!administrationForm.isRestrictEdit()) {
            boolean convertResult = convertUserPassword(administrationForm.getUser().getUserPassword());
            if (!convertResult) {
                return Constants.FAILURE;
            }
        }
        service.updateUser(administrationForm.getUser());
        return SUCCESS;
    }

    /**
     * User registration. Converts original password to md5
     * @return SUCCESS constant in case of success flow, FAILURE - otherwise
     */
    public String registerUser() {
        boolean convertResult = convertUserPassword(administrationForm.getUser().getUserPassword());
        if (!convertResult) {
            return Constants.FAILURE;
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
