package com.example.customeronboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer onboardCustomer(Customer customer) {
        // Critical business logic: check for duplicate email
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        // Age eligibility check
        if (customer.getAge() < 18 || customer.getAge() > 60) {
            throw new IllegalArgumentException("Customer age must be between 18 and 60");
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
} 