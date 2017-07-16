package com.angular.services.dao;

import com.angular.services.entity.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Darsh on 5/13/2017.
 */
@Repository
public interface UserDao extends JpaRepository<UserActionEntity,String>{
}
