package org.aub.db.dao.impl;

import com.mongodb.DB;
import org.aub.db.dao.AdvertDao;
import org.aub.db.domain.Advert;
import org.aub.db.util.MongoUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class AdvertDaoImpl implements AdvertDao {

    @Inject
    private MongoUtil mongoUtil;

    @Override
    public List<Advert> getAll() {
        DB db = mongoUtil.getDb();
        return null;
    }

}
