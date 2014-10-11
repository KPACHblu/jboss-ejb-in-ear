package org.aub.db.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.aub.db.dao.AdvertProfileDao;
import org.aub.db.domain.AdvertProfile;
import org.aub.db.exception.PersistenceException;
import org.aub.db.util.MongoUtil;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AdvertProfileDaoImpl implements AdvertProfileDao {

    private DBCollection dbCollection;

    @Inject
    private MongoUtil mongoUtil;

    @PostConstruct
    private void init() {
        dbCollection = mongoUtil.getDb().getCollection(AdvertProfile.COLLECTION_NAME);
    }


    @Override
    public AdvertProfile create(AdvertProfile entity) throws PersistenceException {
        try {
            dbCollection.insert(entity.toDBObject());
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return entity;
    }

    @Override
    public AdvertProfile update(AdvertProfile entity) throws PersistenceException {
        dbCollection.update(new BasicDBObject("_id", entity.getId()), entity.toDBObject());
        return entity;
    }

    @Override
    public void delete(AdvertProfile entity) throws PersistenceException {
        dbCollection.remove(entity.toDBObject());
    }

    @Override
    public List<AdvertProfile> findAll() {
        List<AdvertProfile> allEntities = new ArrayList<AdvertProfile>();
        DBCursor cursor = dbCollection.find();
        try {
            while (cursor.hasNext()) {
                allEntities.add(AdvertProfile.fromDBObject(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return allEntities;
    }

    @Override
    public AdvertProfile findById(String id) {
        return AdvertProfile.fromDBObject(dbCollection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }
}
