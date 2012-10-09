package com.issoft.services;

import com.issoft.entity.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Common interface for all services
 * @param <T> Entity type
 * @param <K> Entity id type
 */
public abstract class CommonService<T,K> {
    /**
     * DAO to use in appropriate service
     */
    protected CommonDAO<T,K> dao;

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
