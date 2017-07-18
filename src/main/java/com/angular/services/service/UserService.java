package com.angular.services.service;

import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserActionEntity;
import com.angular.services.entity.UserEntity;
import com.angular.services.response.UserProfileResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.angular.services.constants.MainConstants.CLIENT_ID;

/**
 * Created by Darsh on 5/15/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private static final JacksonFactory JACKSON_FACTORY = new JacksonFactory();

    public void initialLogin(String userIdToken){

        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JACKSON_FACTORY).setAudience(Collections.singletonList(CLIENT_ID)).build();

        try {
            GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(userIdToken);

            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();

                String userId = payload.getSubject();
                UserEntity alreadyExistingUser = userDao.findOne(userId);

                if (alreadyExistingUser != null) {
                    alreadyExistingUser.setLoginCount(alreadyExistingUser.getLoginCount() + 1);
                    userDao.save(alreadyExistingUser);
                } else {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setLoginCount(1);
                    userEntity.setUserId(userId);
                    userDao.save(userEntity);
                }
            }
        }
        catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public UserProfileResponse userProfile(String userIdToken){
        return new UserProfileResponse();
    }

    public void addFavourite(UserActionEntity userActionEntity){
    }

    public void sublistingViewed(UserActionEntity userActionEntity){
    }

}
