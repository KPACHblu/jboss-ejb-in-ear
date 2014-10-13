package org.aub.db.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Advert {

    public static final String COLLECTION_NAME = "advert";

    private ObjectId id;
    //TODO Add unique index
    private String url;
    //TODO Add AdvertProfileId field
    private Date createdDate;

    //TODO use Morphia or some other approach to avoid these mapping
    public DBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("_id", (id == null) ? new ObjectId() : id);
        document.put("url", url);
        document.put("createdDate", createdDate);

        return document;
    }

    public static Advert fromDBObject(DBObject document) {
        Advert object = new Advert();

        object.setId((ObjectId) document.get("_id"));
        object.setUrl((String) document.get("url"));
        object.setCreatedDate((Date) document.get("createdDate"));

        return object;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

}
