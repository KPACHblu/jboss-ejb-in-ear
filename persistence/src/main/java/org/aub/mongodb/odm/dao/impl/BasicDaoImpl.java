package org.aub.mongodb.odm.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.aub.mongodb.odm.dao.BasicDao;
import org.aub.mongodb.odm.exception.PersistenceException;
import org.aub.mongodb.odm.mapping.IdMapper;
import org.aub.mongodb.odm.mapping.ObjectMapper;
import org.aub.mongodb.odm.mapping.TableMapper;
import org.aub.mongodb.odm.util.Persistence;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicDaoImpl<T> implements BasicDao<T> {

    @Override
    public DBCollection getCollection() {
        return getDB().getCollection(TableMapper.getEntityTableName(getObjectClass()));
    }

    @Override
    public T toEntity(DBObject document) {
        return ObjectMapper.toEntity(document, getObjectClass());
    }

    @Override
    public DBObject toDBObject(T entity) {
        return ObjectMapper.toDbObject(entity);
    }

    @Override
    public T create(T entity) throws PersistenceException {
        try {
            getCollection().insert(toDBObject(entity));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return entity;
    }

    @Override
    public T update(T entity) throws PersistenceException {
        try {
            getCollection().update(new BasicDBObject(IdMapper.getEntityIdFieldName(getObjectClass()), IdMapper.getEntityIdFieldValue(entity)), toDBObject(entity));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return entity;
    }

    @Override
    public void delete(T entity) throws PersistenceException {
        try {
            getCollection().remove(toDBObject(entity));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<T> findAll() {
        List<T> allEntities = new ArrayList<>();
        DBCursor cursor = getCollection().find();
        try {
            while (cursor.hasNext()) {
                allEntities.add(this.<T>toEntity(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return allEntities;
    }

    @Override
    public T findById(Object id) {
        String fieldName = IdMapper.getEntityIdFieldName(getObjectClass());
        DBObject resultObject = getCollection().findOne(new BasicDBObject(fieldName, id));
        return toEntity(resultObject);
    }

    private Class<T> getObjectClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> objectType = (Class<T>) type.getActualTypeArguments()[0];
        return objectType;
    }

}
