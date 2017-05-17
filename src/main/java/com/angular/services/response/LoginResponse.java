package com.angular.services.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Darsh on 5/17/2017.
 */
@Component
public class LoginResponse implements Serializable{

    private String status;
    private String user_name;
    private String user_email;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
