package org.aub.db.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.aub.db.dao.BasicDao;
import org.aub.db.exception.PersistenceException;
import org.aub.db.odm.mapping.IdMapper;
import org.aub.db.odm.mapping.ObjectMapper;
import org.aub.db.odm.mapping.TableMapper;

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
            //TODO Implement interceptor instead of try-catch
            throw new PersistenceException(e);
        }
        return entity;
    }

    @Override
    public T update(T entity) throws PersistenceException {
        getCollection().update(new BasicDBObject(IdMapper.getEntityIdFieldName(getObjectClass()), IdMapper.getEntityIdFieldValue(entity)), toDBObject(entity));
        return entity;
    }

    @Override
    public void delete(T entity) throws PersistenceException {
        getCollection().remove(toDBObject(entity));
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
        DBObject objectToFind = new BasicDBObject(fieldName, id);
        DBObject resultObject = getCollection().findOne(objectToFind);
        return toEntity(resultObject);
    }

    private Class<T> getObjectClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> objectType = (Class<T>) type.getActualTypeArguments()[0];
        return objectType;
    }

}
