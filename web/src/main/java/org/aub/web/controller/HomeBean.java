package org.aub.web.controller;

import org.aub.db.domain.Advert;
import org.aub.db.domain.AdvertProfile;
import org.aub.db.exception.PersistenceException;
import org.aub.service.AdvertProfileService;
import org.aub.service.AdvertService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//TODO Get rid of session scope
@Named
@SessionScoped
public class HomeBean implements Serializable{

    private AdvertProfile currentAdvertProfile;

    @Inject
    private AdvertService advertService;
    @Inject
    private AdvertProfileService advertProfileService;

    public List<Advert> getNewAdverts(AdvertProfile profile) {
        if (profile == null || profile.getId() == null) {
            return new ArrayList<Advert>();
        } else {
            return advertService.getNewAdverts(profile);
        }
    }

    public List<AdvertProfile> findAll() {
        return advertProfileService.findAll();
    }

    public void delete(AdvertProfile entity) throws PersistenceException {
        advertProfileService.delete(entity);
    }

    public AdvertProfile getCurrentAdvertProfile() {
        return (currentAdvertProfile != null) ? currentAdvertProfile : new AdvertProfile();
    }

    public void setCurrentAdvertProfile(AdvertProfile currentAdvertProfile) {
        this.currentAdvertProfile = currentAdvertProfile;
    }
}
