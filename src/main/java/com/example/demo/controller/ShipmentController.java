package com.example.demo.controller;

import com.example.demo.dto.ShipmentDto;
import com.example.demo.dto.TrackingDto;
import com.example.demo.entity.ShipmentEntity;
import com.example.demo.entity.TrackingEntity;
import com.example.demo.service.ShipmentService;
import com.example.demo.status.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ShipmentController {
    private ShipmentService shipmentService;
    //private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ShipmentController( final ShipmentService shipmentService ) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("api/shipment/{id:\\d+}")
    ResponseEntity<ShipmentDto> readShipment(@PathVariable("id") Long id ) {

        Optional<ShipmentEntity> opt = shipmentService.getShipmentById( id );
        if( opt.isPresent() ) {

            ShipmentEntity shipmentEntity = opt.get();

            return ResponseEntity.ok( toShipmentDto( shipmentEntity) );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("api/shipment")
    ResponseEntity<List<ShipmentDto>> readAllShipment() {

        List<ShipmentDto> shipmentList = shipmentService.getShipmentAll()
                .stream()
                .map( ShipmentController::toShipmentDto )
                .collect( Collectors.toList());

        return ResponseEntity.ok( shipmentList );
    }

    @PostMapping("api/shipment")
    ResponseEntity<ShipmentDto> createShipment( @RequestBody ShipmentDto shipmentDto ) {

        ShipmentEntity shipmentEntity = toShipment(shipmentDto);
        ShipmentEntity stored = shipmentService.storeShipment(shipmentEntity);

        return ResponseEntity.ok( toShipmentDto(stored) );
    }

    @DeleteMapping("api/shipment/{shipmentId:\\d+}")
    ResponseEntity<?> deleteShipment( @PathVariable("shipmentId") final Long id ) {

        Optional<ShipmentEntity> opt = shipmentService.getShipmentById( id );

        if( opt.isPresent() ) {
            shipmentService.deleteShipment( opt.get() );
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("api/shipment/{shipmentId:\\d+}")
    ResponseEntity<ShipmentDto> updateShipment( @PathVariable("shipmentId") final Long id, @RequestBody ShipmentDto shipmentDto ) {

        Optional<ShipmentEntity> opt = shipmentService.getShipmentById( id );

        if( opt.isPresent() ) {

            ShipmentEntity shipmentEntity = opt.get();
            shipmentEntity.setTitle( shipmentDto.getTitle() );
            shipmentService.storeShipment(shipmentEntity);
            ShipmentDto updatedDto = toShipmentDto(shipmentEntity);
            return ResponseEntity.ok(updatedDto);
        }

        return ResponseEntity.notFound().build();
    }

    private static ShipmentDto toShipmentDto( ShipmentEntity shipmentEntity ) {
        String name = shipmentEntity.getTitle();
        Long shipmentEntityId = shipmentEntity.getId();
        List<TrackingDto> shipmentDtos = shipmentEntity
                .getTrackings().stream()
                .map( tracking -> new TrackingDto(tracking.getTracking(), Status.of(tracking.getStatus()) ))
                .collect(Collectors.toList());

        return  new ShipmentDto(shipmentEntityId, name,shipmentDtos);
    }

    private static ShipmentEntity toShipment( ShipmentDto shipmentDto ) {
        return new ShipmentEntity();
    }
}
