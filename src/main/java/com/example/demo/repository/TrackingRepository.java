package com.example.demo.repository;

import com.example.demo.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {
}
