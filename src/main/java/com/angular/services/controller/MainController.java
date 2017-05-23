package com.angular.services.controller;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserEntity;
import com.angular.services.response.LoginResponse;
import com.angular.services.response.MainTopicResponse;
import com.angular.services.response.UserServiceResponse;
import com.angular.services.service.TopicsService;
import com.angular.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    TopicsService topicsService;

    @RequestMapping(value="/api/save",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public UserServiceResponse addUser(@RequestBody UserEntity userEntity){
        String status = userService.validateUserAndAdd(userEntity);
        userServiceResponse.setStatus(status);
        return userServiceResponse;
    }

    @RequestMapping(value="/api/login",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public LoginResponse loginUser(@RequestBody UserEntity userEntity){
        return userService.validateLogin(userEntity);
    }

    @RequestMapping(value="/api/updateuser",method= RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public UserServiceResponse updateUser(@RequestBody UserEntity userEntity) {
        String status = userService.updateUserDetails(userEntity);
        userServiceResponse.setStatus(status);
        return userServiceResponse;
    }

    @RequestMapping(value = "/api/mainTopics",method = RequestMethod.GET,produces = "application/json")
    public List<MainTopicResponse> getAllTopics(){
        return topicsService.getAllActiveTopics();
    }


}
