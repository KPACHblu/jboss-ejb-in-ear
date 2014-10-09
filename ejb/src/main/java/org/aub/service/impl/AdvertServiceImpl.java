package org.aub.service.impl;

import org.aub.db.dao.AdvertDao;
import org.aub.db.dao.impl.AdvertDaoImpl;
import org.aub.service.AdvertService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AdvertServiceImpl implements AdvertService {

    @Inject
    private AdvertDaoImpl advertDao;

    @Override
    public List<String> getAllAdvertByUrl(String url) {
        advertDao.getAll();
        List<String> resultUrl = new ArrayList<>();
        resultUrl.add("http://google.com");
        return resultUrl;
    }
}
