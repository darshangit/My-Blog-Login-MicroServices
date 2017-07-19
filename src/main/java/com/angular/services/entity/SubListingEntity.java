package com.angular.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Darsh on 6/4/2017.
 */
@Entity
@Table(name = "SUB_LISTINGS")
public class SubListingEntity {

    private Integer subListingUUID;
    private String subListingName;
    private String subListingStatus;
    private String listingLinks;


    @Column(name = "SUB_LISTING_ID")
    public Integer getSubListingUUID() {
        return subListingUUID;
    }

    @Id
    @Column(name="LISTING_NAME")
    public String getSubListingName() {
        return subListingName;
    }

    @Column(name="LISTING_STATUS")
    public String getSubListingStatus() {
        return subListingStatus;
    }

    @Column(name="LISTING_LINKS")
    public String getListingLinks() {
        return listingLinks;
    }

    public void setSubListingUUID(Integer subListingUUID) {
        this.subListingUUID = subListingUUID;
    }

    public void setSubListingName(String subListingName) {
        this.subListingName = subListingName;
    }

    public void setSubListingStatus(String subListingStatus) {
        this.subListingStatus = subListingStatus;
    }

    public void setListingLinks(String listingLinks) {
        this.listingLinks = listingLinks;
    }
}
