package com.issoft.entity;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
  * User: nikitadavydov
 * Date: 9/17/12
 */
public class UsersDAOImpl implements UsersDAO {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    //TODO: work with this classes
    @Override
    public boolean add(Users user) {
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Users user = getUserById(id);
        hibernateTemplate.delete(user);
        return true;
    }

    @Override
    public boolean update(Users user) {
        //Users user = getUserById(id);
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public List<Users> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
        List<Users> result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    @Override
    public Users getUserById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
        List<Users> result = hibernateTemplate.findByCriteria(criteria.add(Property.forName("id").eq(Long.valueOf(id))));
        return result.get(0);
    }

    //TODO: move to UserRolesImpl???
    @Override
    public List<UserRole> getUserRoles() {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserRole.class);
        List<UserRole> result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    @Override
    public UserRole getUserRoleById(long id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserRole.class);
        List<UserRole> result = hibernateTemplate.findByCriteria(criteria.add(Property.forName("id").eq(id)));
        return result.get(0);
    }
}
