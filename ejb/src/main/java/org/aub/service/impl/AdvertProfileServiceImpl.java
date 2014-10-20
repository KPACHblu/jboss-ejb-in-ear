package org.aub.service.impl;

import org.aub.db.dao.AdvertDao;
import org.aub.db.dao.AdvertProfileDao;
import org.aub.db.domain.AdvertProfile;
import org.aub.db.exception.PersistenceException;
import org.aub.service.AdvertProfileService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.List;

public class AdvertProfileServiceImpl implements AdvertProfileService {
    @Inject
    private AdvertDao advertDao;
    @Inject
    private AdvertProfileDao advertProfileDao;

    @Override
    public AdvertProfile create(AdvertProfile entity) throws PersistenceException {
        return advertProfileDao.create(entity);
    }

    @Override
    public AdvertProfile update(AdvertProfile entity) throws PersistenceException {
        return advertProfileDao.update(entity);
    }

    //TODO BUG! Delete all related adverts as well
    @Override
    public void delete(AdvertProfile entity) throws PersistenceException {
        advertProfileDao.delete(entity);
    }

    @Override
    public List<AdvertProfile> findAll() {
        return advertProfileDao.findAll();
    }

    @Override
    public AdvertProfile findById(Object id) {
        return advertProfileDao.findById(new ObjectId(id.toString()));
    }

}
