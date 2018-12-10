package com.angular.services.controller;

import com.angular.services.entity.BabuaEntity;
import com.angular.services.entity.UserActionEntity;
import com.angular.services.response.UserProfileResponse;
import com.angular.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Darsh on 5/13/2017.
 */
@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/api/userCount/{burnBride}")
    public BabuaEntity updateAndDeleteBride(@PathVariable String burnBride){
        return userService.updateAndDeleteBride(burnBride);
    }

    @PostMapping(value="/api/initialLogin",consumes = "application/json")
    public void initialLogin(@RequestBody String userIdToken){
        userService.initialLogin(userIdToken);
    }

    @PostMapping(value="/api/userProfile",consumes = "application/json",produces = "application/json")
    public UserProfileResponse userProfile(@RequestBody String userIdToken){
        return userService.userProfile(userIdToken);
    }

    @PostMapping(value = "/api/favourite",consumes = "application/json")
    public void addFavourite(@RequestBody UserActionEntity userActionEntity){
        userService.addFavourite(userActionEntity);
    }

    @PostMapping(value = "/api/removeFavourite",consumes = "application/json")
    public void removeFavourite(@RequestBody UserActionEntity userActionEntity){
        userService.removeFavourite(userActionEntity);
    }

    @PostMapping(value = "/api/getFavourite",consumes = "application/json")
    public boolean getFavourite(@RequestBody UserActionEntity userActionEntity){
       return userService.getFavourite(userActionEntity);
    }

    @PostMapping(value = "/api/subListingViews",consumes = "application/json")
    public void sublistingViewed(@RequestBody UserActionEntity userActionEntity){
        userService.sublistingViewed(userActionEntity);
    }
}
