package com.issoft.log.database;

import org.hibernate.SessionFactory;
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
        String query1 = "SELECT user.email, user.userName \n" +
                "FROM UserEntity user \n" +
                "JOIN user.notifications un \n" +
                "WHERE un.notification = ?";

        String[] queryParam = {action};
        List<Object[]> sds = hibernateTemplate.find(query1, queryParam);
        return sds;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}
