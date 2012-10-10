package com.issoft.entity.dao;

import com.issoft.entity.EmailNotification;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Notification DAO implementation
 */
public class EmailNotificationDAOImpl extends CommonDAO<EmailNotification, Integer> {
    /**
     * Gets list of entities
     *
     * @return Entities list
     */
    @Override
    public List<EmailNotification> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(EmailNotification.class);
        List<EmailNotification> notifications = hibernateTemplate.findByCriteria(criteria);
        return notifications;
    }

    /**
     * Gets entity by id
     *
     * @param id Entity id
     * @return Entity with specified id
     */
    @Override
    public EmailNotification getById(Integer id) {
        return null;
    }

    /**
     * Save or updates specified entity
     *
     * @param entity Entity to save/update
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    @Override
    public boolean update(EmailNotification entity) {
        return false;
    }

    /**
     * Deletes entity with specified id
     *
     * @param id Entity id
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
