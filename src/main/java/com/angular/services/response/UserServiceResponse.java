package com.angular.services.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Darsh on 5/15/2017.
 */
@Component
public class UserServiceResponse implements Serializable{

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
