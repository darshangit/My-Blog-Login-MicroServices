package com.angular.services.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Darsh on 5/13/2017.
 */
@Entity
@Table(name="USER_INFO")
public class UserEntity implements Serializable{

    private String userId;
    private Integer loginCount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID")
    public String getUserId() {
        return userId;
    }

    @Column(name="LOGIN_COUNT")
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

}
