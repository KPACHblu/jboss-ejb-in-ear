package org.aub.db.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.aub.db.dao.AdvertDao;
import org.aub.db.domain.Advert;
import org.aub.db.odm.mapping.Mapper;
import org.aub.db.util.MongoUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AdvertDaoImpl extends BasicDaoImpl<Advert> implements AdvertDao {

    @Inject
    private MongoUtil mongoUtil;

    @PostConstruct
    private void init() {
        getCollection().createIndex(new BasicDBObject("url", 1), new BasicDBObject("unique", Boolean.TRUE));
    }

    @Override
    public DB getDB() {
        return mongoUtil.getDb();
    }
}
