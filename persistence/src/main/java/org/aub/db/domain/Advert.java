package org.aub.db.domain;

import org.aub.db.odm.annotation.Column;
import org.aub.db.odm.annotation.Table;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

@Table(name="advert")
public class Advert extends BaseEntity implements Serializable {

    @Column(name = "profile_id")
    private ObjectId advertProfileId;
    private String url;
    @Column(name = "created_date")
    private Date createdDate;

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
