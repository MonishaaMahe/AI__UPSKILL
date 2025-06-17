package com.example.customermanagement.controller;

import com.example.customermanagement.exception.CustomerException;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Customer management operations.
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.debug("Received request to create customer: {}", customer.getEmail());
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    /**
     * Get a customer by ID.
     *
     * @param id the customer ID
     * @return the customer if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        log.debug("Received request to get customer with ID: {}", id);
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all customers.
     *
     * @return list of all customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.debug("Received request to get all customers");
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Update a customer.
     *
     * @param id the customer ID
     * @param customer the updated customer data
     * @return the updated customer
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody Customer customer) {
        log.debug("Received request to update customer with ID: {}", id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete a customer.
     *
     * @param id the customer ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("Received request to delete customer with ID: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Exception handler for CustomerException.
     *
     * @param ex the exception
     * @return error response
     */
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<String> handleCustomerException(CustomerException ex) {
        log.error("Customer operation failed: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
} 