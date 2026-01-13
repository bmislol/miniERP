package com.charbel.backend.service;

import org.springframework.stereotype.Service;

import com.charbel.backend.entity.Customer;
import com.charbel.backend.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
