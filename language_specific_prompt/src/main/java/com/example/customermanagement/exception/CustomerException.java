package com.example.customermanagement.exception;

/**
 * Custom exception class for customer-related errors.
 */
public class CustomerException extends RuntimeException {
    
    public CustomerException(String message) {
        super(message);
    }
    
    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
} 