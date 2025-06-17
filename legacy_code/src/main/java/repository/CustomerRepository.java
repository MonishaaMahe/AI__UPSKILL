package repository;

import model.Customer;
import java.util.Optional;

/**
 * Repository interface for customer-related database operations.
 */
public interface CustomerRepository {
    /**
     * Finds a customer by their ID.
     * @param customerId The ID of the customer to find
     * @return An Optional containing the customer if found, empty otherwise
     * @throws DatabaseException if there is an error accessing the database
     */
    Optional<Customer> findById(String customerId);
} 