package org.aub.db.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

public class Advert implements Serializable {

    public static final String COLLECTION_NAME = "advert";

    private ObjectId id;
    private ObjectId advertProfileId;
    private String url;
    private Date createdDate;

    public static Advert fromDBObject(DBObject document) {
        Advert object = new Advert();

        object.setId((ObjectId) document.get("_id"));
        object.setAdvertProfileId((ObjectId) document.get("profile_id"));
        object.setUrl((String) document.get("url"));
        object.setCreatedDate((Date) document.get("createdDate"));

        return object;
    }

    //TODO use Morphia or some other approach to avoid these mapping
    public DBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("_id", (id == null) ? new ObjectId() : id);
        document.put("profile_id", advertProfileId);
        document.put("url", url);
        document.put("createdDate", createdDate);

        return document;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getAdvertProfileId() {
        return advertProfileId;
    }

    public void setAdvertProfileId(ObjectId advertProfileId) {
        this.advertProfileId = advertProfileId;
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
