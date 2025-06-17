package com.payment.strategy;

import com.payment.model.Payment;
import com.payment.exception.PaymentException;

/**
 * Interface defining the contract for all payment strategies.
 */
public interface PaymentStrategy {
    /**
     * Process a payment
     * @param payment The payment to process
     * @return The processed payment
     * @throws PaymentException if payment processing fails
     */
    Payment processPayment(Payment payment) throws PaymentException;
} 