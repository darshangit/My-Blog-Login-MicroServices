package com.angular.services.response;

import org.springframework.stereotype.Component;

/**
 * Created by Darsh on 5/24/2017.
 */
@Component
public class MainTopicResponse {

    private Integer uuid;
    private String name;
    private String imgSrc;
    private String status;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
