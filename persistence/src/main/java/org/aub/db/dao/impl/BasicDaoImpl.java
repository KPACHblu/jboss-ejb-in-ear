package org.aub.db.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.aub.db.dao.BasicDao;
import org.aub.db.domain.BaseEntity;
import org.aub.db.exception.PersistenceException;
import org.aub.db.odm.mapping.Mapper;
import org.bson.types.ObjectId;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicDaoImpl<T extends BaseEntity> implements BasicDao<T> {

    @Override
    public DBCollection getCollection() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> objectType = (Class<T>) type.getActualTypeArguments()[0];
        return getDB().getCollection(Mapper.getEntityTableName(objectType));
    }

    public <T> T toEntity(DBObject document) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> objectType = (Class<T>) type.getActualTypeArguments()[0];
        return Mapper.toEntity(document, objectType);
    }

    public DBObject toDBObject(T entity) {
        return Mapper.toDbObject(entity);
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
        //TODO Remove entity.getId()
        getCollection().update(new BasicDBObject("_id", entity.getId()), toDBObject(entity));
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
    public T findById(String id) {
        //TODO Remove hardcoded ID field
        return toEntity(getCollection().findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

}
