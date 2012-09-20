package com.issoft.log.database;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * @author: AS
 */
public class UserEntityDAOImpl implements UserEntityDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * Returns list of users subscribed for certain action.
     *
     * @param action
     */
    @Override
    public List<UserEntity> selectReceivers(String action) {
        String query = "FROM UserEntity user WHERE email_notification LIKE ?";
        String[] queryParam = {"%" + action + "%"};
        return (List<UserEntity>) hibernateTemplate.find(query, queryParam);
    }

}
