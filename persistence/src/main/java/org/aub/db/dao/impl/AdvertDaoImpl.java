package org.aub.db.dao.impl;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.aub.db.dao.AdvertDao;
import org.aub.db.domain.Advert;
import org.aub.db.util.MongoUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

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
    public Advert create(Advert entity) {
        try {
            WriteResult result = dbCollection.insert(entity.toDBObject());
        } catch (Exception e) {
            System.out.println(e);
        }
        return entity;
    }

    @Override
    public List<Advert> getAll() {
        return null;
    }

}
