package com.issoft.services;

import com.issoft.entity.EmailNotification;
import com.issoft.entity.dao.CommonHibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class NotificationService extends CommonService<EmailNotification, Integer> {
    @Autowired
    @Qualifier("notificationDAO")
    public void setDao(CommonHibernateDAO dao) {
        this.dao = dao;
    }

    /**
     * Gets list of entities
     *
     * @return Entities list
     */
    @Override
    public List<EmailNotification> list() {
        return dao.list();
    }

    /**
     * Gets entity by id
     *
     * @param id Entity id
     * @return Entity with specified id
     */
    @Override
    public EmailNotification getById(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Save or updates specified entity
     *
     * @param entity Entity to save/update
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    @Override
    public boolean update(EmailNotification entity) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Deletes entity with specified id
     *
     * @param id Entity id
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    @Override
    public boolean delete(Integer id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
