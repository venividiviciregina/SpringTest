package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.ShipmentDto;
import com.example.demo.dto.TrackingDto;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private CustomerService customerService;

    // private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CustomerController( final CustomerService customerService ) {
        this.customerService = customerService;
    }

    private static CustomerDto toCustomer( final CustomerEntity customerEntity ) {

        String name = customerEntity.getName();
        Long customer = customerEntity.getId();

        List<ShipmentDto> shipmentDtos = customerEntity.getShipments()
                .stream()
                .map( shipment -> new ShipmentDto(shipment.getId(), shipment.getTitle(), shipment.getTrackings()
                        .stream()
                        .map( tr -> new TrackingDto(tr.getTracking(), tr.getStatusEnum() ) )
                        .collect(Collectors.toList())) )
                .collect(Collectors.toList());

        return new CustomerDto(customer , name, shipmentDtos);

    }

    private static CustomerEntity toCustomerEntity( CustomerDto dto ) {
        return new CustomerEntity();
    }
    @GetMapping("api/customer/{id:\\d+}")
    ResponseEntity<CustomerDto> readCustomer( @PathVariable("id") Long id ) {

        Optional<CustomerEntity> opt = customerService.getCustomerById( id );
        if( opt.isPresent() ) {

            CustomerDto customerDto = toCustomer(opt.get());
            return ResponseEntity.ok( customerDto );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("api/customer")
    ResponseEntity<List<CustomerDto>> readAllCustomer() {

        List<CustomerDto> customerList = customerService.getCustomerAll()
                .stream()
                .map( CustomerController::toCustomer )
                .collect( Collectors.toList());

        return ResponseEntity.ok( customerList );
    }

    @PostMapping("api/customer")
    ResponseEntity<CustomerDto> createCustomer( @RequestBody CustomerDto customerDto ) {

        CustomerEntity customerEntity = toCustomerEntity(customerDto);
        CustomerDto createdDto =  toCustomer(customerService.createCustomer( customerEntity));
        return ResponseEntity.ok( createdDto );

    }

    @DeleteMapping("api/customer/{customerId:\\d+}")
    ResponseEntity<?> deleteCustomer( @PathVariable("customerId") final Long id ) {

        Optional<CustomerEntity> opt = customerService.getCustomerById( id );

        if( opt.isPresent() ) {
            customerService.deleteCustomer( opt.get() );
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("api/customer/{customerId:\\d+}")
    ResponseEntity<CustomerDto> updateCustomer( @PathVariable("customerId") final Long id, @RequestBody CustomerDto customerDto ) {

        Optional<CustomerEntity> opt = customerService.getCustomerById( id );

        if( opt.isPresent() ) {

            CustomerEntity customerEntity = opt.get();
            customerEntity.setName( customerDto.getName() );
            customerService.storeCustomer(customerEntity);
            CustomerDto updatedDto = toCustomer(customerEntity);
            return ResponseEntity.ok(updatedDto);
        }

        return ResponseEntity.notFound().build();
    }
}
