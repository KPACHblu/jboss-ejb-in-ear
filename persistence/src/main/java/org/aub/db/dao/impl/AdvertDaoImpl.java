package org.aub.db.dao.impl;

import com.mongodb.DBCollection;
import org.aub.db.dao.AdvertDao;
import org.aub.db.domain.Advert;
import org.aub.db.exception.PersistenceException;
import org.aub.db.util.MongoUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AdvertDaoImpl implements AdvertDao {

    private DBCollection dbCollection;

    @Inject
    private MongoUtil mongoUtil;

    @PostConstruct
    private void init() {
        dbCollection = mongoUtil.getDb().getCollection(Advert.COLLECTION_NAME);
    }

    @Override
    public Advert create(Advert entity) throws PersistenceException {
        try {
            dbCollection.insert(entity.toDBObject());
        } catch (Exception e) {
            //TODO Implement interceptor instead of try-catch
            throw new PersistenceException(e);
        }
        return entity;
    }

    @Override
    public void delete(Advert entity) throws PersistenceException {
        dbCollection.remove(entity.toDBObject());
    }

}
