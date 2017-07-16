package com.angular.services.service;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserActionEntity;
import com.angular.services.response.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Darsh on 5/15/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void initialLogin(String userIdToken){
    }

    public UserProfileResponse userProfile(String userIdToken){
        return new UserProfileResponse();
    }

    public void addFavourite(UserActionEntity userActionEntity){
    }

    public void sublistingViewed(UserActionEntity userActionEntity){
    }

}
