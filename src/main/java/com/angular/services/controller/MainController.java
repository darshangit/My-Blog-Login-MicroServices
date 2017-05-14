package com.angular.services.controller;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Darsh on 5/13/2017.
 */
@RestController
public class MainController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value="/api/save",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public String addUser(@RequestBody UserEntity userEntity){
        System.out.println("adding"+userEntity);
        userDao.save(userEntity);
        return "Done";
    }

}
