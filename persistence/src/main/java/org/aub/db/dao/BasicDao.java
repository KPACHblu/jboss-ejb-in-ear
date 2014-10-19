package org.aub.db.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.aub.db.exception.PersistenceException;

import java.util.List;

public interface BasicDao<T> {

    DB getDB();

    DBCollection getCollection();

    T create(T entity) throws PersistenceException;

    T update(T entity) throws PersistenceException;

    void delete(T entity) throws PersistenceException;

    List<T> findAll();

    T findById(String id);
}
