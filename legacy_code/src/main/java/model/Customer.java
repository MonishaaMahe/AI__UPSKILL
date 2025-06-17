package model;

/**
 * Represents a customer in the system.
 * @param id The unique identifier of the customer
 * @param name The customer's name
 * @param email The customer's email address
 */
public record Customer(
    String id,
    String name,
    String email
) {
    /**
     * Validates the customer data.
     * @throws IllegalArgumentException if any field is invalid
     */
    public Customer {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or blank");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
} 