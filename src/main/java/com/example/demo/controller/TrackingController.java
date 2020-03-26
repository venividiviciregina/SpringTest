package com.example.demo.controller;

import com.example.demo.dto.TrackingDto;
import com.example.demo.status.Status;
import com.example.demo.entity.TrackingEntity;
import com.example.demo.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TrackingController {

   //private ModelMapper modelMapper = new ModelMapper();
    private TrackingService trackingService;

    @Autowired
    public TrackingController (TrackingService service){
        this.trackingService=service;
    }

    private static TrackingDto trackingDto( TrackingEntity trackingEntity ) {

        return new TrackingDto(trackingEntity.getTracking(), Status.of(trackingEntity.getStatus()));
    }

    private static TrackingEntity toEntity( TrackingDto dto ) {

        return new TrackingEntity();
    }

    @GetMapping("api/tracking/{id:\\d+}")
    ResponseEntity<TrackingDto> readTracking(@PathVariable("id") Long id ) {

        Optional<TrackingEntity> opt = trackingService.getTrackingById( id );
        if( opt.isPresent() ) {

            return ResponseEntity.ok( trackingDto(opt.get()) );
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/api/tracking")
    List<TrackingDto> readTrackingAll() {
        List<TrackingDto> trackingList = trackingService.getTrackingAll()
                .stream()
                .map( TrackingController::trackingDto )
                .collect( Collectors.toList());

        return trackingList;
    }

    @PostMapping("/api/tracking")
    TrackingDto createTracking(@RequestBody TrackingDto trackingDto) {
        System.out.println(trackingDto);


        TrackingEntity trackingEntity = toEntity(trackingDto);
        System.out.println(trackingEntity);
        return trackingDto(trackingService.createTracking(trackingEntity) );
    }

    @DeleteMapping("api/tracking/{trackingId:\\d+}")
    ResponseEntity<?> deleteTracking( @PathVariable("trackingId") final Long id ) {

        Optional<TrackingEntity> opt = trackingService.getTrackingById( id );

        if( opt.isPresent() ) {
            trackingService.deleteTracking( opt.get() );
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("api/tracking/{trackingId:\\d+}")
    ResponseEntity<TrackingDto> updateTracking( @PathVariable("trackingId") final Long id, @RequestBody TrackingDto trackingDto ) {

        Optional<TrackingEntity> opt = trackingService.getTrackingById( id );

        if( opt.isPresent() ) {

            TrackingEntity trackingEntity = opt.get();
            trackingEntity.setStatus( trackingEntity.getStatus() );
            trackingService.storeTracking(trackingEntity);
            TrackingDto updatedDto = trackingDto(trackingEntity);
            return ResponseEntity.ok(updatedDto);
        }

        return ResponseEntity.notFound().build();
    }
}
