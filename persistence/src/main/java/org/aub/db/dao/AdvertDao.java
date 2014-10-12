package org.aub.db.dao;

import org.aub.db.domain.Advert;
import org.aub.db.exception.PersistenceException;

public interface AdvertDao {

    Advert create(Advert entity) throws PersistenceException;

    void delete(Advert entity) throws PersistenceException;

}
