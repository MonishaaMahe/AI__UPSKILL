package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for Customer-related business operations.
 */
public interface CustomerService {
    
    /**
     * Create a new customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    Customer createCustomer(Customer customer);
    
    /**
     * Get a customer by their ID.
     *
     * @param id the customer ID
     * @return Optional containing the customer if found
     */
    Optional<Customer> getCustomerById(Long id);
    
    /**
     * Get all customers.
     *
     * @return list of all customers
     */
    List<Customer> getAllCustomers();
    
    /**
     * Update an existing customer.
     *
     * @param id the customer ID
     * @param customer the updated customer data
     * @return the updated customer
     */
    Customer updateCustomer(Long id, Customer customer);
    
    /**
     * Delete a customer.
     *
     * @param id the customer ID
     */
    void deleteCustomer(Long id);
} 