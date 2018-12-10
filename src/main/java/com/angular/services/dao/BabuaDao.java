package com.angular.services.dao;

import com.angular.services.entity.BabuaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BabuaDao extends JpaRepository<BabuaEntity, String> {
}
