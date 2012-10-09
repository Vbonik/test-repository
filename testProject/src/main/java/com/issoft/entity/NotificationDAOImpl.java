package com.issoft.entity;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Notification DAO implementation
 */
public class NotificationDAOImpl extends CommonDAO<Notification, Integer> {
    /**
     * Gets list of entities
     *
     * @return Entities list
     */
    @Override
    public List<Notification> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
        List<Notification> notifications = hibernateTemplate.findByCriteria(criteria);
        return notifications;
    }

    /**
     * Gets entity by id
     *
     * @param id Entity id
     * @return Entity with specified id
     */
    @Override
    public Notification getById(Integer id) {
        return null;
    }

    /**
     * Save or updates specified entity
     *
     * @param entity Entity to save/update
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    @Override
    public boolean update(Notification entity) {
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
