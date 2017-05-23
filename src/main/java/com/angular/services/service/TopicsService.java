package com.angular.services.service;

import com.angular.services.dao.MainTopicsDao;
import com.angular.services.entity.MainTopicsEntity;
import com.angular.services.response.MainTopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.angular.services.constants.MainConstants.ACTIVE;

/**
 * Created by Darsh on 5/24/2017.
 */
@Service
public class TopicsService {

    @Autowired
    private MainTopicsDao mainTopicsDao;

    public List<MainTopicResponse> getAllActiveTopics(){
        List<MainTopicsEntity> mainTopicResponses =  mainTopicsDao.findByStatus(ACTIVE);

        final List<MainTopicResponse> responseList = new ArrayList<>();

        mainTopicResponses.stream().forEach(mainTopicsEntity -> {
            final MainTopicResponse mainTopicResponse = new MainTopicResponse();
            mainTopicResponse.setName(mainTopicsEntity.getTopicName());
            mainTopicResponse.setStatus(mainTopicsEntity.getStatus());
            mainTopicResponse.setImgSrc(mainTopicsEntity.getImgSrc());
            mainTopicResponse.setUuid(mainTopicsEntity.getUUID());
            responseList.add(mainTopicResponse);
        });
        return responseList;
    }
}
