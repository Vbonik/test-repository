package com.issoft.services;

import com.issoft.entity.User;
import com.issoft.entity.UserRole;
import com.issoft.entity.UsersDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 10/1/12
 */
public class AdministrationService extends ActionSupport implements Service {

    private static final Logger logger = Logger.getLogger(AdministrationService.class);

    private UsersDAO dao;

    public AdministrationService() {
    }

    public List<User> getUserList() {
        return dao.list();
    }

    public List<UserRole> getUserRoleList() {
        return dao.getUserRoles();
    }

    public User getUserById(String id) {
        return dao.getUserById(id);
    }

    public boolean updateUser(User user) {
        if (user.getUser_roles().getId() == 0) {
            user.setUser_roles(dao.getUserRoleById(1));
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

    public UsersDAO getDao() {
        return dao;
    }

    public void setDao(UsersDAO dao) {
        this.dao = dao;
    }
}
