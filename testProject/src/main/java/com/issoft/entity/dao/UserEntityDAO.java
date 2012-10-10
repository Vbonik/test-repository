package com.issoft.entity.dao;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;

import java.util.List;

/**
 * @author: AS
 */
public interface UserEntityDAO {
    public boolean add(UserEntity user);

    public boolean delete(String userId);

    public boolean update(UserEntity user);

    public List list();

    public UserEntity getUserById(String userId);

    public List<UserRole> getUserRoles();

    public UserRole getUserRoleById(long id);

    List<Object[]> selectReceivers(String action);
}
