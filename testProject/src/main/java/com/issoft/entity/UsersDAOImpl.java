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

    @Override
    public boolean add(User user) {
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public boolean delete(String user_id) {
        User user = getUserById(user_id);
        hibernateTemplate.delete(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public List<User> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        List<User> result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    //TODO: find user by string id
    @Override
    public User getUserById(String user_id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        List<User> result = hibernateTemplate.findByCriteria(criteria.add(Property.forName("user_id").eq(user_id)));
        return result.get(0);
    }

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
