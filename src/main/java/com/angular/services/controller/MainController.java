package com.angular.services.controller;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserActionEntity;
import com.angular.services.response.UserProfileResponse;
import com.angular.services.response.UserServiceResponse;
import com.angular.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Darsh on 5/13/2017.
 */
@RestController
public class MainController {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    UserServiceResponse userServiceResponse;

    @RequestMapping(name = "/api/initialLogin")
    public void initialLogin(@RequestBody String userIdToken){
        userService.initialLogin(userIdToken);
    }

    @RequestMapping(name="/api/userProfile")
    public UserProfileResponse userProfile(@RequestBody String userIdToken){
        return userService.userProfile(userIdToken);
    }

    @RequestMapping(name = "/api/favourite")
    public void addFavourite(@RequestBody UserActionEntity userActionEntity){
        userService.addFavourite(userActionEntity);

    }

    @RequestMapping(name = "/api/subListingViews")
    public void sublistingViewed(@RequestBody UserActionEntity userActionEntity){
        userService.sublistingViewed(userActionEntity);
    }
}
