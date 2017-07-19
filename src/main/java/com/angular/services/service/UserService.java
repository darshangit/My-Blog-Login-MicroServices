package com.angular.services.service;

import com.angular.services.dao.SubListingDao;
import com.angular.services.dao.UserActionDao;
import com.angular.services.dao.UserDao;
import com.angular.services.entity.UserActionEntity;
import com.angular.services.entity.UserEntity;
import com.angular.services.response.UserProfileResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.angular.services.constants.MainConstants.CLIENT_ID;
import static com.angular.services.constants.MainConstants.YES;

/**
 * Created by Darsh on 5/15/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserActionDao userActionDao;

    @Autowired
    private SubListingDao subListingDao;

    private static final JacksonFactory JACKSON_FACTORY = new JacksonFactory();

    public void initialLogin(String userIdToken){

        try {
            String userId = getUserId(userIdToken);
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
        catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public UserProfileResponse userProfile(String userIdToken){
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        try {
            String userId = getUserId(userIdToken);
            UserEntity userEntity = userDao.findOne(userId);

            if (userEntity != null) {
                userProfileResponse.setTotalSiteVisits(userEntity.getLoginCount());
                userProfileResponse.setTotalTopicsPresent(subListingDao.count());
                List<UserActionEntity> userActionEntityList = userActionDao.findByUserIdOrderByCreateTimestampAsc(userId);
                if(!CollectionUtils.isEmpty(userActionEntityList)){
                    userProfileResponse.setTotalTopicsViewed(userActionEntityList.stream().count());
                    userProfileResponse.setRecentlyViewed(userActionEntityList.stream().limit(5).collect(Collectors.toList()));
                    userProfileResponse.setFavourites(userActionEntityList.stream().filter(entry -> YES.equals(entry.getFavourite())).collect(Collectors.toList()));
                    userProfileResponse.setLearningLevel(calculateLearningPercentage(userProfileResponse.getTotalTopicsPresent(),userProfileResponse.getTotalTopicsViewed()));
                }
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return userProfileResponse;
    }

    public void addFavourite(UserActionEntity userActionEntity){
        try {
            String userId = getUserId(userActionEntity.getUserId());
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userId,userActionEntity.getListingName());
            dbUserActionEntity.setFavourite(YES);
            userActionDao.save(dbUserActionEntity);
        } catch (GeneralSecurityException |IOException e) {
            e.printStackTrace();
        }
    }

    public void sublistingViewed(UserActionEntity userActionEntity){
        try {
            String userId = getUserId(userActionEntity.getUserId());
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userId,userActionEntity.getListingName());
            if(dbUserActionEntity == null){
                userActionEntity.setUserId(userId);
                userActionDao.save(userActionEntity);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long calculateLearningPercentage(Long totalTopicsPresent, Long totalTopicsViewed) {
        return (totalTopicsViewed/totalTopicsPresent) * 100;
    }

    public String getUserId(String incomingUserId) throws GeneralSecurityException, IOException {
        String userId = null;
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JACKSON_FACTORY).setAudience(Collections.singletonList(CLIENT_ID)).build();
        GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(incomingUserId);
        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            userId = payload.getSubject();
        }
        return userId;
    }
}
