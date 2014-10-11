package org.aub.service;

import org.aub.db.domain.Advert;
import org.aub.db.domain.AdvertProfile;

import java.io.Serializable;
import java.util.List;

public interface AdvertService extends Serializable{

    public List<Advert> getNewAdverts(AdvertProfile profile);

}
