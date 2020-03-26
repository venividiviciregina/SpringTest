package com.example.demo.service;

import com.example.demo.entity.TrackingEntity;
import com.example.demo.entity.TrackingEntity;
import com.example.demo.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;
    public TrackingEntity createTracking(final TrackingEntity trackingEntity ) {
        return trackingRepository.save( trackingEntity );
    }

    public Optional<TrackingEntity> getTrackingById(final Long id) {
        return Optional.of(trackingRepository.getOne(id));
    }
    public void deleteTracking( final TrackingEntity trackingEntity ) {

        trackingRepository.delete( trackingEntity );
    }

    public TrackingEntity storeTracking(final TrackingEntity  trackingEntity) {
        return trackingRepository.save(trackingEntity);
    }
    
    public List<TrackingEntity> getTrackingAll() {
        return trackingRepository.findAll();
    }

}
