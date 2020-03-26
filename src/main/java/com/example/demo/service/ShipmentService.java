package com.example.demo.service;

import com.example.demo.entity.ShipmentEntity;
import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public ShipmentEntity createShipment( ShipmentEntity shipmentEntity ) {
        return shipmentRepository.save( shipmentEntity );
    }

    public Optional<ShipmentEntity> getShipmentById(final Long id ) {
        return Optional.of(shipmentRepository.getOne( id ));
    }
    public ShipmentEntity storeShipment( ShipmentEntity shipmentEntity ) {
        return shipmentRepository.save(shipmentEntity);
    }
    public void deleteShipment(final ShipmentEntity shipmentEntity) {
        shipmentRepository.delete(shipmentEntity);
    }
    public List<ShipmentEntity> getShipmentAll() {
        return shipmentRepository.findAll();
    }
}
