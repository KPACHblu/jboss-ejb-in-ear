package org.aub.service;

import org.aub.db.domain.AdvertProfile;
import org.aub.db.exception.PersistenceException;

import java.io.Serializable;
import java.util.List;

public interface AdvertProfileService extends Serializable {

    AdvertProfile create(AdvertProfile entity) throws PersistenceException;

    AdvertProfile update(AdvertProfile entity) throws PersistenceException;

    void delete(AdvertProfile entity) throws PersistenceException;

    List<AdvertProfile> findAll();

    AdvertProfile findById(String id);
}
