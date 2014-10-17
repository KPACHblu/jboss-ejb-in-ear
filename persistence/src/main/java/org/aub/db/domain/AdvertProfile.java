package org.aub.db.domain;

import org.aub.db.odm.annotation.Column;
import org.aub.db.odm.annotation.Table;

import java.io.Serializable;

@Table(name="advert_profile")
public class AdvertProfile extends BaseEntity implements Serializable {

    private String name;
    @Column(name = "site_url")
    private String siteUrl;
    @Column(name = "advert_pattern")
    private String advertPattern;
    @Column(name = "search_url")
    private String searchUrl;
    @Column(name = "search_url_page_param")
    private String searchUrlPageParam;
    @Column(name = "search_pages_number")
    private Long searchPagesNumber;

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

}
