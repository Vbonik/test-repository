package com.issoft.services;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;

import java.util.List;

/**
 * User: nikitadavydov
 * Date: 10/1/12
 */
public interface Service {
    public List<UserEntity> getUserList();

    public List<UserRole> getUserRoleList();

    public UserEntity getUserById(String id);

    public boolean updateUser(UserEntity user);

    public boolean deleteUser(String id);
}
