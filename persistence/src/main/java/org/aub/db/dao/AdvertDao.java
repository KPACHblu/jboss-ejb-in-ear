package org.aub.db.dao;

import org.aub.db.domain.Advert;

import java.util.List;

public interface AdvertDao {

    Advert create(Advert entity);

    public List<Advert> getAll();

}
