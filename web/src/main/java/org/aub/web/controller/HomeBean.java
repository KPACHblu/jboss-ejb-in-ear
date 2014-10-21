package org.aub.web.controller;

import org.aub.db.domain.Advert;
import org.aub.db.domain.AdvertProfile;
import org.aub.mongodb.odm.exception.PersistenceException;
import org.aub.service.AdvertProfileService;
import org.aub.service.AdvertService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO Get rid of session scope
@Named
@SessionScoped
public class HomeBean implements Serializable{

    private AdvertProfile currentAdvertProfile;
    private List<Advert> newAdverts = new ArrayList<>();

    @Inject
    private AdvertService advertService;
    @Inject
    private AdvertProfileService advertProfileService;


    public List<AdvertProfile> findAll() {
        return advertProfileService.findAll();
    }

    public void search(AdvertProfile entity) {
        if (entity != null || entity.getId() != null) {
            setCurrentAdvertProfile(entity);
            setNewAdverts(advertService.getNewAdverts(getCurrentAdvertProfile()));
        }
    }

    public String edit(AdvertProfile entity) throws PersistenceException {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("advertProfile", entity);
        return "advertProfile";
    }

    public AdvertProfile getCurrentAdvertProfile() {
        return (currentAdvertProfile != null) ? currentAdvertProfile : new AdvertProfile();
    }

    public void setCurrentAdvertProfile(AdvertProfile currentAdvertProfile) {
        this.currentAdvertProfile = currentAdvertProfile;
    }

    public List<Advert> getNewAdverts() {
        return newAdverts;
    }

    public void setNewAdverts(List<Advert> newAdverts) {
        this.newAdverts = newAdverts;
    }
}
