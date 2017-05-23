package com.angular.services.dao;

import com.angular.services.entity.MainTopicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Darsh on 5/24/2017.
 */
@Repository
public interface MainTopicsDao extends JpaRepository<MainTopicsEntity, Long> {

    List<MainTopicsEntity> findByStatus(String status);
}
