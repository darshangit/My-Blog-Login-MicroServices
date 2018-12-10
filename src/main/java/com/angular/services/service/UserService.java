package com.angular.services.service;

import com.angular.services.dao.BabuaDao;
import com.angular.services.dao.SubListingDao;
import com.angular.services.dao.UserActionDao;
import com.angular.services.dao.UserDao;
import com.angular.services.entity.BabuaEntity;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.angular.services.constants.MainConstants.CLIENT_ID_2;
import static com.angular.services.constants.MainConstants.NO;
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

    @Autowired
    private BabuaDao babuaDao;

    private static final JacksonFactory JACKSON_FACTORY = new JacksonFactory();

    public void initialLogin(String userIdToken){

        try {
            UserEntity alreadyExistingUser = userDao.findOne(userIdToken);

            if (alreadyExistingUser != null) {
                alreadyExistingUser.setLoginCount(alreadyExistingUser.getLoginCount() + 1);
                userDao.save(alreadyExistingUser);
            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setLoginCount(1);
                userEntity.setUserId(userIdToken);
                userDao.save(userEntity);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BabuaEntity updateAndDeleteBride(String response){
        BabuaEntity babuaEntity = babuaDao.findOne("Babua");

        if("Yes".equals(response)){
            babuaEntity.setLoadOrNot("Yes");
        }else {
            babuaEntity.setLoginCount(babuaEntity.getLoginCount()+1);
        }
        babuaDao.save(babuaEntity);

        return babuaEntity;
    }

    public UserProfileResponse userProfile(String userIdToken){
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        try {
            UserEntity userEntity = userDao.findOne(userIdToken);

            if (userEntity != null) {
                userProfileResponse.setTotalSiteVisits(userEntity.getLoginCount());
                userProfileResponse.setTotalTopicsPresent(subListingDao.count());
                List<UserActionEntity> userActionEntityList = userActionDao.findByUserIdOrderByCreateTimestampAsc(userIdToken);
                if(!CollectionUtils.isEmpty(userActionEntityList)){
                    userProfileResponse.setTotalTopicsViewed(userActionEntityList.stream().count());
                    userProfileResponse.setRecentlyViewed(userActionEntityList.stream().limit(5).collect(Collectors.toList()));
                    userProfileResponse.setFavourites(userActionEntityList.stream().filter(entry -> YES.equals(entry.getFavourite())).collect(Collectors.toList()));
                    userProfileResponse.setLearningLevel(calculateLearningPercentage(userProfileResponse.getTotalTopicsPresent(),userProfileResponse.getTotalTopicsViewed()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfileResponse;
    }

    public void addFavourite(UserActionEntity userActionEntity){
        try {
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userActionEntity.getUserId(),userActionEntity.getListingName());
            dbUserActionEntity.setFavourite(YES);
            userActionEntity.setCreateTimestamp(new Timestamp(Instant.now().toEpochMilli()));
            userActionDao.save(dbUserActionEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFavourite(UserActionEntity userActionEntity){
        try {
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userActionEntity.getUserId(),userActionEntity.getListingName());
            dbUserActionEntity.setFavourite(NO);
            userActionEntity.setCreateTimestamp(new Timestamp(Instant.now().toEpochMilli()));
            userActionDao.save(dbUserActionEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getFavourite(UserActionEntity userActionEntity){
        try {
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userActionEntity.getUserId(),userActionEntity.getListingName());
            if(dbUserActionEntity != null && YES.equals(dbUserActionEntity.getFavourite())){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sublistingViewed(UserActionEntity userActionEntity){
        try {
            UserActionEntity dbUserActionEntity = userActionDao.findByUserIdEqualsAndListingNameEquals(userActionEntity.getUserId(),userActionEntity.getListingName());
            if(dbUserActionEntity == null){
                userActionEntity.setUserId(userActionEntity.getUserId());
                userActionEntity.setCreateTimestamp(new Timestamp(Instant.now().toEpochMilli()));
                userActionDao.save(userActionEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long calculateLearningPercentage(Long totalTopicsPresent, Long totalTopicsViewed) {
        return (new BigDecimal(totalTopicsViewed).divide(new BigDecimal(totalTopicsPresent),2, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).longValue();
    }

    public String getUserId(String incomingUserId) throws GeneralSecurityException, IOException {
        String userId = null;
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JACKSON_FACTORY).setIssuer("https://accounts.google.com").setAudience(Collections.singletonList(CLIENT_ID_2)).build();
        GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(incomingUserId);
        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            userId = payload.getSubject();
        }
        return userId;
    }
}
