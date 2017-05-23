package com.angular.services.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Darsh on 5/24/2017.
 */
@Entity
@Table(name="MAIN_TOPICS")
public class MainTopicsEntity implements Serializable{

    private Integer UUID;
    private String topicName;
    private String imgSrc;
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOPIC_UUID")
    public Integer getUUID() {
        return UUID;
    }

    @Column(name="TOPIC_NAME")
    public String getTopicName() {
        return topicName;
    }

    @Column(name = "IMG_SRC")
    public String getImgSrc() {
        return imgSrc;
    }

    @Column(name="STATUS")
    public String getStatus() {
        return status;
    }

    public void setUUID(Integer UUID) {
        this.UUID = UUID;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
