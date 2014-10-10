package org.aub.service;

import org.aub.db.domain.Advert;

import java.util.List;

public interface AdvertService {

    public List<Advert> getAllAdvertByUrl(String url);

}
