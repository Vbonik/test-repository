package com.issoft.entity;

import java.util.List;

/**
  * User: nikitadavydov
 * Date: 9/17/12
  */
public interface UsersDAO {

    public boolean add(User user);

    public boolean delete(String user_id);

    public boolean update(User user);

    public List list();

    public User getUserById(String user_id);

    public List<UserRole> getUserRoles();

    public UserRole getUserRoleById(long id);
}
