package org.aub.web.converter;

import org.aub.db.domain.AdvertProfile;
import org.aub.service.AdvertProfileService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AdvertProfileConverter implements Converter {

    @Inject
    private AdvertProfileService advertProfileService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String string) {
        return (string != null && !string.isEmpty()) ? advertProfileService.findById(string) : "";
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String result = null;
        if (object != null && object instanceof AdvertProfile) {
            result = ((AdvertProfile) object).getId();
        }
        return result;
    }
}
