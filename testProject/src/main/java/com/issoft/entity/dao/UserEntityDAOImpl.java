package com.issoft.entity.dao;

import com.issoft.entity.UserEntity;
import com.issoft.entity.UserRole;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * @author: AS
 */
public class UserEntityDAOImpl implements UserEntityDAO {

    private HibernateTemplate hibernateTemplate;

    /**
     * Returns name and email of user subscribed for certain action.
     *
     * @param action
     */
    @Override
    public List<Object[]> selectReceivers(String action) {
        String query1 = "SELECT user.email, user.userId \n" +
                "FROM UserEntity user \n" +
                "JOIN user.notifications un \n" +
                "WHERE un.notification = ?";

        String[] queryParam = {action};
        List<Object[]> sds = hibernateTemplate.find(query1, queryParam);
        return sds;
    }

    @Override
    public boolean add(UserEntity user) {
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public boolean delete(String user_id) {
        UserEntity user = getUserById(user_id);
        hibernateTemplate.delete(user);
        return true;
    }

    @Override
    public boolean update(UserEntity user) {
        hibernateTemplate.saveOrUpdate(user);
        return true;
    }

    @Override
    public List<UserEntity> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserEntity.class);
        List<UserEntity> result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    //TODO: find user by string id
    @Override
    public UserEntity getUserById(String user_id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserEntity.class);
        List<UserEntity> result = hibernateTemplate.findByCriteria(criteria.add(Property.forName("userId").eq(user_id)));
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    
}
