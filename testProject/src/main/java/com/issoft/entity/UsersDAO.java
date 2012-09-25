package com.issoft.entity;

import java.util.List;

/**
  * User: nikitadavydov
 * Date: 9/17/12
  */
public interface UsersDAO {

    public boolean add(Users user);

    public boolean delete(int id);

    public boolean update(Users user);

    public List list();

    public Users getUserById(int id);

    public List<UserRole> getUserRoles();

    public UserRole getUserRoleById(long id);

    public boolean getUserStatus(long id);
}
