package org.aub.web.controller;

import org.aub.db.domain.AdvertProfile;
import org.aub.mongodb.odm.exception.PersistenceException;
import org.aub.service.AdvertProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class AdvertProfileBean implements Serializable {


    private AdvertProfile currentAdvertProfile;

    @Inject
    private AdvertProfileService advertProfileService;

    public String create(AdvertProfile entity) throws PersistenceException {
        advertProfileService.create(entity);
        return "home";
    }

    public void update(AdvertProfile entity) throws PersistenceException {
        advertProfileService.update(entity);
    }

    public AdvertProfile getCurrentAdvertProfile() {
        if (currentAdvertProfile == null) {
            currentAdvertProfile = new AdvertProfile();
        }
        return currentAdvertProfile;
    }

    public void setCurrentAdvertProfile(AdvertProfile currentAdvertProfile) {
        this.currentAdvertProfile = currentAdvertProfile;
    }


}
