package com.angular.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dash on 7/16/2017.
 */
@Entity
@Table(name="USER_ACTIONS")
public class UserActionEntity {

    private String userId;
    private String listingName;
    private String listingLink;
    private String favourite;

    @Column(name="USER_ID")
    public String getUserId() {
        return userId;
    }

    @Id
    @Column(name="LISTING_NAME")
    public String getListingName() {
        return listingName;
    }

    @Column(name="LISTING_LINK")
    public String getListingLink() {
        return listingLink;
    }

    @Column(name="FAVOURITE")
    public String getFavourite() {
        return favourite;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public void setListingLink(String listingLink) {
        this.listingLink = listingLink;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }
}
