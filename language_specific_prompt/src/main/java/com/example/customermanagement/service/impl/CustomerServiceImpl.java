package com.example.customermanagement.service.impl;

import com.example.customermanagement.exception.CustomerException;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CustomerService interface.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        log.debug("Creating new customer: {}", customer.getEmail());
        
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Customer with email " + customer.getEmail() + " already exists");
        }
        
        try {
            Customer savedCustomer = customerRepository.save(customer);
            log.info("Created new customer with ID: {}", savedCustomer.getId());
            return savedCustomer;
        } catch (Exception e) {
            log.error("Error creating customer: {}", e.getMessage());
            throw new CustomerException("Error creating customer", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(Long id) {
        log.debug("Fetching customer with ID: {}", id);
        return customerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        log.debug("Fetching all customers");
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, Customer customer) {
        log.debug("Updating customer with ID: {}", id);
        
        return customerRepository.findById(id)
            .map(existingCustomer -> {
                if (!existingCustomer.getEmail().equals(customer.getEmail()) &&
                    customerRepository.existsByEmail(customer.getEmail())) {
                    throw new CustomerException("Email " + customer.getEmail() + " is already in use");
                }
                
                existingCustomer.setFirstName(customer.getFirstName());
                existingCustomer.setLastName(customer.getLastName());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                existingCustomer.setAddress(customer.getAddress());
                existingCustomer.setActive(customer.isActive());
                
                try {
                    Customer updatedCustomer = customerRepository.save(existingCustomer);
                    log.info("Updated customer with ID: {}", id);
                    return updatedCustomer;
                } catch (Exception e) {
                    log.error("Error updating customer: {}", e.getMessage());
                    throw new CustomerException("Error updating customer", e);
                }
            })
            .orElseThrow(() -> new CustomerException("Customer not found with ID: " + id));
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        log.debug("Deleting customer with ID: {}", id);
        
        if (!customerRepository.existsById(id)) {
            throw new CustomerException("Customer not found with ID: " + id);
        }
        
        try {
            customerRepository.deleteById(id);
            log.info("Deleted customer with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting customer: {}", e.getMessage());
            throw new CustomerException("Error deleting customer", e);
        }
    }
} 