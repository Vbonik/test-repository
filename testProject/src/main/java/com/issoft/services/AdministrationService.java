package com.issoft.services;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;
import com.issoft.entity.dao.UserEntityDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 10/1/12
 */
public class AdministrationService extends ActionSupport implements Service {

    private static final Logger logger = Logger.getLogger(AdministrationService.class);

    private UserEntityDAO dao;

    public AdministrationService() {
    }

    public List<UserEntity> getUserList() {
        return dao.list();
    }

    public List<UserRole> getUserRoleList() {
        return dao.getUserRoles();
    }

    public UserEntity getUserById(String id) {
        return dao.getUserById(id);
    }

    public boolean updateUser(UserEntity user) {
        if (user.getUser_roles() == null || user.getUser_roles().getId() == 0) {
            user.setUser_roles(dao.getUserRoleById(3));
        }
        return dao.update(user);
    }

    public boolean deleteUser(String id) {
        boolean deleteFlag = false;
        if (!"admin".equals(id)) {
            deleteFlag = dao.delete(id);
        }
        return deleteFlag;
    }

    public UserEntityDAO getDao() {
        return dao;
    }

    @Autowired
    @Qualifier(value = "userDAO")
    public void setDao(UserEntityDAO dao) {
        this.dao = dao;
    }
}
