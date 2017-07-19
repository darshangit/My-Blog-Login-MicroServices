package com.angular.services.response;

import com.angular.services.entity.UserActionEntity;

import java.util.List;

/**
 * Created by Dash on 7/16/2017.
 */
public class UserProfileResponse {

    private Integer totalSiteVisits;
    private Long totalTopicsPresent;
    private Long totalTopicsViewed;
    private Long learningLevel;

    private List<UserActionEntity> recentlyViewed;
    private List<UserActionEntity> favourites;


    public Integer getTotalSiteVisits() {
        return totalSiteVisits;
    }

    public void setTotalSiteVisits(Integer totalSiteVisits) {
        this.totalSiteVisits = totalSiteVisits;
    }

    public Long getTotalTopicsPresent() {
        return totalTopicsPresent;
    }

    public void setTotalTopicsPresent(Long totalTopicsPresent) {
        this.totalTopicsPresent = totalTopicsPresent;
    }

    public Long getTotalTopicsViewed() {
        return totalTopicsViewed;
    }

    public void setTotalTopicsViewed(Long totalTopicsViewed) {
        this.totalTopicsViewed = totalTopicsViewed;
    }

    public Long getLearningLevel() {
        return learningLevel;
    }

    public void setLearningLevel(Long learningLevel) {
        this.learningLevel = learningLevel;
    }

    public List<UserActionEntity> getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(List<UserActionEntity> recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }

    public List<UserActionEntity> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<UserActionEntity> favourites) {
        this.favourites = favourites;
    }
}
