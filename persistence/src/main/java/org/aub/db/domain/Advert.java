package org.aub.db.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

public class Advert {

    public static final String COLLECTION_NAME = "adverts";

    private String url;
    private Date createdDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    //TODO use Morphia to avoid these mapping
    public DBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("_id", url);
        document.put("createdDate", createdDate);

        return document;
    }

    public static Advert fromDBObject(DBObject document) {
        Advert object = new Advert();

        object.setUrl((String)document.get("_id"));
        object.setCreatedDate((Date)document.get("createdDate"));

        return object;
    }
}
