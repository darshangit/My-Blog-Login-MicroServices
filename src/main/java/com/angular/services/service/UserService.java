package com.angular.services.service;

import com.angular.services.constants.MainConstants;
import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserEntity;
import com.angular.services.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Darsh on 5/15/2017.
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public String validateUserAndAdd(UserEntity userEntity){
        UserEntity entity = userDao.findByEmail(userEntity.getEmail());
        String status = MainConstants.ALREADY_EXISTS;

        if(entity == null){
            userDao.save(userEntity);
            status = MainConstants.SUCCESS;
        }
        return status;
    }

    public LoginResponse validateLogin(UserEntity userEntity){
        UserEntity entity = userDao.findByEmail(userEntity.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        String status = "Invalid user name and password";

        if(entity == null){
            status = "Email is not registered, Please Sign up";
        }
        else if(entity.getEmail().equals(userEntity.getEmail())
                && entity.getPassword().equals(userEntity.getPassword())){
            status = "Logged In Successfully";

            loginResponse.setStatus(status);
            loginResponse.setUserEmail(entity.getEmail());
            loginResponse.setUserName(entity.getName());
            return loginResponse;
        }
        loginResponse.setStatus(status);
        return loginResponse;
    }
}
