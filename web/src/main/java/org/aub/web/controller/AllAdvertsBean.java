package org.aub.web.controller;

import org.aub.db.domain.Advert;
import org.aub.service.AdvertService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AllAdvertsBean {

    @Inject
    private AdvertService advertService;

    public List<Advert> getAllAdverts() {
        return advertService.getAllAdvertByUrl("Some url");
    }
}
