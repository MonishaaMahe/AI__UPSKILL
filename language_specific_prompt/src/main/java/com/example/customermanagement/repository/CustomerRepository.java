package com.example.customermanagement.repository;

import com.example.customermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Customer entity.
 * Provides data access methods for Customer operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    /**
     * Find a customer by their email address.
     *
     * @param email the email address to search for
     * @return Optional containing the customer if found
     */
    Optional<Customer> findByEmail(String email);
    
    /**
     * Check if a customer exists with the given email.
     *
     * @param email the email address to check
     * @return true if a customer exists with the email
     */
    boolean existsByEmail(String email);
} 