package org.aub.db.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.aub.db.exception.PersistenceException;

import java.util.List;

public interface BasicDao<T> {

    T toEntity(DBObject document);

    DBObject toDBObject(T entity);

    DB getDB();

    DBCollection getCollection();

    T create(T entity) throws PersistenceException;

    T update(T entity) throws PersistenceException;

    void delete(T entity) throws PersistenceException;

    List<T> findAll();

    T findById(Object id);
}
