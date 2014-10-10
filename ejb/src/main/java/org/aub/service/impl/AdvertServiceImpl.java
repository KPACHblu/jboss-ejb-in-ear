package org.aub.service.impl;

import org.apache.commons.io.IOUtils;
import org.aub.db.dao.AdvertDao;
import org.aub.db.dao.impl.AdvertDaoImpl;
import org.aub.db.domain.Advert;
import org.aub.service.AdvertService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class AdvertServiceImpl implements AdvertService {
    private static final String URL_FOR_PARSE = "http://kharkov.kha.olx.ua/nedvizhimost/prodazha-domov/prodazha-kottedzhey/?search%5Bfilter_float_price%3Afrom%5D=300000&search%5Bfilter_float_price%3Ato%5D=500000&search%5Bphotos%5D=1";
    private static final String WEB_SITE_ENCODING = "UTF-8";

    @Inject
    private AdvertDao advertDao;

    @Override
    public List<Advert> getAllAdvertByUrl(String url) {
        List<Advert> adverts = null;
        //TODO throw errors to the client side
        try {
            InputStream in = new URL(URL_FOR_PARSE).openStream();
            String page;
            try {
                page = IOUtils.toString(in, WEB_SITE_ENCODING);
                adverts = getAdvertsBySourcePage(page);
            } finally {
                IOUtils.closeQuietly(in);
            }

        } catch (Exception e) {

        }
        return adverts;
    }


    private List<Advert> getAdvertsBySourcePage(String page) {
        List<Advert> adverts = new ArrayList<>();
        Pattern titlePattern = Pattern.compile("http://kharkov.kha.olx.ua/obyavlenie/.*.html");
        Matcher matcher = titlePattern.matcher(page);
        while (matcher.find()) {
            Advert currentAdvert = new Advert();
            currentAdvert.setUrl(matcher.group());
            currentAdvert.setCreatedDate(new Date());
            advertDao.create(currentAdvert);
            adverts.add(currentAdvert);
        }

        return adverts;
    }
}
