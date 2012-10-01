package com.issoft.services;

import com.issoft.entity.User;
import com.issoft.entity.UserRole;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 10/1/12
 */
public interface Service {
    public List<User> getUserList();

    public List<UserRole> getUserRoleList();

    public User getUserById(String id);

    public boolean updateUser(User user);

    public boolean deleteUser(String id);
}
