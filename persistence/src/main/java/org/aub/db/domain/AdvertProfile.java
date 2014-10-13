package org.aub.db.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class AdvertProfile implements Serializable {

    public static final String COLLECTION_NAME = "advert_profile";

    private ObjectId id;
    private String name;
    private String siteUrl;
    private String advertPattern;
    private String searchUrl;
    private String searchUrlPageParam;
    private Long searchPagesNumber;

    public DBObject toDBObject() {
        BasicDBObject document = new BasicDBObject();

        document.put("_id", (id == null) ? new ObjectId() : id);
        document.put("name", name);
        document.put("siteUrl", siteUrl);
        document.put("advertPattern", advertPattern);
        document.put("searchUrl", searchUrl);
        document.put("searchUrlPageParam", searchUrlPageParam);
        document.put("searchPagesNumber", searchPagesNumber);

        return document;
    }

    public static AdvertProfile fromDBObject(DBObject document) {
        AdvertProfile object = new AdvertProfile();

        object.setId((ObjectId) document.get("_id"));
        object.setName((String) document.get("name"));
        object.setSiteUrl((String) document.get("siteUrl"));
        object.setAdvertPattern((String) document.get("advertPattern"));
        object.setSearchUrl((String) document.get("searchUrl"));
        object.setSearchUrlPageParam((String) document.get("searchUrlPageParam"));
        object.setSearchPagesNumber((Long) document.get("searchPagesNumber"));

        return object;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getAdvertPattern() {
        return advertPattern;
    }

    public void setAdvertPattern(String advertPattern) {
        this.advertPattern = advertPattern;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getSearchUrlPageParam() {
        return searchUrlPageParam;
    }

    public void setSearchUrlPageParam(String searchUrlPageParam) {
        this.searchUrlPageParam = searchUrlPageParam;
    }

    public Long getSearchPagesNumber() {
        return searchPagesNumber;
    }

    public void setSearchPagesNumber(Long searchPagesNumber) {
        this.searchPagesNumber = searchPagesNumber;
    }


    //TODO Use apache lib for generation equals hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertProfile profile = (AdvertProfile) o;

        if (advertPattern != null ? !advertPattern.equals(profile.advertPattern) : profile.advertPattern != null)
            return false;
        if (id != null ? !id.equals(profile.id) : profile.id != null) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (searchPagesNumber != null ? !searchPagesNumber.equals(profile.searchPagesNumber) : profile.searchPagesNumber != null)
            return false;
        if (searchUrl != null ? !searchUrl.equals(profile.searchUrl) : profile.searchUrl != null) return false;
        if (searchUrlPageParam != null ? !searchUrlPageParam.equals(profile.searchUrlPageParam) : profile.searchUrlPageParam != null)
            return false;
        if (siteUrl != null ? !siteUrl.equals(profile.siteUrl) : profile.siteUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (siteUrl != null ? siteUrl.hashCode() : 0);
        result = 31 * result + (advertPattern != null ? advertPattern.hashCode() : 0);
        result = 31 * result + (searchUrl != null ? searchUrl.hashCode() : 0);
        result = 31 * result + (searchUrlPageParam != null ? searchUrlPageParam.hashCode() : 0);
        result = 31 * result + (searchPagesNumber != null ? searchPagesNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdvertProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", advertPattern='" + advertPattern + '\'' +
                ", searchUrl='" + searchUrl + '\'' +
                ", searchUrlPageParam='" + searchUrlPageParam + '\'' +
                ", searchPagesNumber=" + searchPagesNumber +
                '}';
    }
}
