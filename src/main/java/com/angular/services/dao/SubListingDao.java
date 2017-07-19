package com.angular.services.dao;

import com.angular.services.entity.SubListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Darsh on 6/4/2017.
 */
@Repository
public interface SubListingDao extends JpaRepository<SubListingEntity,Long>{
}
