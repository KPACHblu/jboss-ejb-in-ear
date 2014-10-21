package org.aub.web.controller;

import org.aub.db.domain.AdvertProfile;
import org.aub.mongodb.odm.exception.PersistenceException;
import org.aub.service.AdvertProfileService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@RequestScoped
public class AdvertProfileBean implements Serializable {

    private AdvertProfile currentAdvertProfile;

    @Inject
    private AdvertProfileService advertProfileService;

    @PostConstruct
    private void init() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        currentAdvertProfile = (AdvertProfile) sessionMap.get("advertProfile");
    }

    public String save(AdvertProfile profile) throws PersistenceException {
        if (profile.getId() == null) {
            create(profile);
        } else {
            update(profile);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("advertProfile");
        return "home";
    }

    public String delete(AdvertProfile entity) throws PersistenceException {
        advertProfileService.delete(entity);
        return "home";
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

    private void create(AdvertProfile entity) throws PersistenceException {
        advertProfileService.create(entity);
    }

    private void update(AdvertProfile entity) throws PersistenceException {
        advertProfileService.update(entity);
    }


}
