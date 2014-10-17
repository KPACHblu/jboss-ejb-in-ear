package org.aub.db.dao.impl;

import com.mongodb.DBCollection;
import org.aub.db.dao.AdvertProfileDao;
import org.aub.db.domain.AdvertProfile;
import org.aub.db.odm.mapping.Mapper;
import org.aub.db.util.MongoUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class AdvertProfileDaoImpl extends BasicDaoImpl<AdvertProfile> implements AdvertProfileDao {

    private DBCollection dbCollection;

    @Inject
    private MongoUtil mongoUtil;

    @PostConstruct
    private void init() {
        //TODO Move this to the BasicDAO
        dbCollection = mongoUtil.getDb().getCollection(Mapper.getEntityTableName(AdvertProfile.class));
    }

    @Override
    public DBCollection getCollection() {
        return dbCollection;
    }

}
