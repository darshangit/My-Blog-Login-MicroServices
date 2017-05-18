package com.angular.services.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Darsh on 5/17/2017.
 */
@Component
public class LoginResponse implements Serializable{

    private String status;
    private String userName;
    private String userEmail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
