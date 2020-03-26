package com.example.demo.service;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity createCustomer( final CustomerEntity customerEntity ) {
        return customerRepository.save( customerEntity );
    }

    public Optional<CustomerEntity> getCustomerById( final Long id ) {


        return Optional.of( customerRepository.getOne( id ));
    }

    public void deleteCustomer( final CustomerEntity customerEntity ) {

        customerRepository.delete( customerEntity );
    }

    public CustomerEntity storeCustomer(final CustomerEntity  customerEntity) {
        return customerRepository.save(customerEntity);
    }

    public List<CustomerEntity> getCustomerAll()  {
        return customerRepository.findAll();
    }

}
