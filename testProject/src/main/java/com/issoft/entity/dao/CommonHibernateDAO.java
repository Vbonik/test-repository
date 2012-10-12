package com.issoft.entity.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Common abstract class for DAO
 * @param <T> Entity type
 * @param <K> Entity id type
 */
public abstract class CommonHibernateDAO<T, K> {
    /**
     * Hibernate template to use
     */
    protected HibernateTemplate hibernateTemplate;

    /**
     * Setter for hibernate template property
     * @param sessionFactory SessionFactory instance
     */
    @Autowired
    public void setHibernateTemplate(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * Gets list of entities
     * @return Entities list
     */
    public abstract List<T> list();

    /**
     * Gets entity by id
     * @param id Entity id
     * @return Entity with specified id
     */
    public abstract T getById(K id);

    /**
     * Save or updates specified entity
     * @param entity Entity to save/update
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    public abstract boolean update(T entity);

    /**
     * Deletes entity with specified id
     * @param id Entity id
     * @return <code>true</code> in success case, <code>false</code> - otherwise
     */
    public abstract boolean delete(K id);
}
