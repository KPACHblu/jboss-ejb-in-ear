package org.aub.db.dao.impl;

import com.mongodb.DB;
import org.aub.db.dao.AdvertProfileDao;
import org.aub.db.domain.AdvertProfile;
import org.aub.db.util.MongoUtil;

import javax.inject.Inject;

public class AdvertProfileDaoImpl extends BasicDaoImpl<AdvertProfile> implements AdvertProfileDao {

    @Inject
    private MongoUtil mongoUtil;

    @Override
    public DB getDB() {
        return mongoUtil.getDb();
    }

}
