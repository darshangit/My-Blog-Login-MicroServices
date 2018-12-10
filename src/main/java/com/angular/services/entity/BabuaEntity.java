package com.angular.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BABUA")
public class BabuaEntity {
    private String userName;
    private Integer loginCount;
    private String loadOrNot;

    @Id
    @Column(name="USER_ID")
    public String getUserName() {
        return userName;
    }

    @Column(name="LOGIN_COUNT")
    public Integer getLoginCount() {
        return loginCount;
    }

    @Column(name="LOAD_OR_NOT")
    public String getLoadOrNot() {
        return loadOrNot;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public void setLoadOrNot(String loadOrNot) {
        this.loadOrNot = loadOrNot;
    }
}
