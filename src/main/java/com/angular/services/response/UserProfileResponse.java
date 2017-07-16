package com.angular.services.response;

import com.angular.services.entity.UserActionEntity;

import java.util.List;

/**
 * Created by Dash on 7/16/2017.
 */
public class UserProfileResponse {

    private Integer totalSiteVisits;
    private Integer totalTopicsPresent;
    private Integer totalTopicsViewed;
    private Integer learningLevel;

    private List<UserActionEntity> recentlyViewedAndFavourites;

    public Integer getTotalSiteVisits() {
        return totalSiteVisits;
    }

    public void setTotalSiteVisits(Integer totalSiteVisits) {
        this.totalSiteVisits = totalSiteVisits;
    }

    public Integer getTotalTopicsPresent() {
        return totalTopicsPresent;
    }

    public void setTotalTopicsPresent(Integer totalTopicsPresent) {
        this.totalTopicsPresent = totalTopicsPresent;
    }

    public Integer getTotalTopicsViewed() {
        return totalTopicsViewed;
    }

    public void setTotalTopicsViewed(Integer totalTopicsViewed) {
        this.totalTopicsViewed = totalTopicsViewed;
    }

    public Integer getLearningLevel() {
        return learningLevel;
    }

    public void setLearningLevel(Integer learningLevel) {
        this.learningLevel = learningLevel;
    }

    public List<UserActionEntity> getRecentlyViewedAndFavourites() {
        return recentlyViewedAndFavourites;
    }

    public void setRecentlyViewedAndFavourites(List<UserActionEntity> recentlyViewedAndFavourites) {
        this.recentlyViewedAndFavourites = recentlyViewedAndFavourites;
    }
}
