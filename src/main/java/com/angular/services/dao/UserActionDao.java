package com.angular.services.dao;

import com.angular.services.entity.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dash on 7/16/2017.
 */
@Repository
public interface UserActionDao extends JpaRepository<UserActionEntity,String> {

    List<UserActionEntity> findByUserIdOrderByCreateTimestampAsc(String userId);
    UserActionEntity findByUserIdEqualsAndListingNameEquals(String userId,String listingName);

}
