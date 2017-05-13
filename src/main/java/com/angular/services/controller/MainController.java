package com.angular.services.controller;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Darsh on 5/13/2017.
 */
@RestController
public class MainController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/save")
    public String add(){
        userDao.save(new UserEntity("dash","asd@gmail.com","asd"));
        return "Done";
    }

}
