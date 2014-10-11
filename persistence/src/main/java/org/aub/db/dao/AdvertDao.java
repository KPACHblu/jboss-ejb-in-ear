package org.aub.db.dao;

import org.aub.db.domain.Advert;
import org.aub.db.exception.PersistenceException;

import java.util.List;

public interface AdvertDao {

    Advert create(Advert entity) throws PersistenceException;

}
