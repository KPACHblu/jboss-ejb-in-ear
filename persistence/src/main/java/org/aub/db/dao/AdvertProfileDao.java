package org.aub.db.dao;

import org.aub.db.domain.AdvertProfile;
import org.aub.db.exception.PersistenceException;

import java.util.List;

public interface AdvertProfileDao {

    AdvertProfile create(AdvertProfile entity) throws PersistenceException;

    AdvertProfile update(AdvertProfile entity) throws PersistenceException;

    void delete(AdvertProfile entity) throws PersistenceException;

    List<AdvertProfile> findAll();

    AdvertProfile findById(String id);
}
