package com.payment.strategy;

import com.payment.model.Payment;
import com.payment.exception.PaymentException;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public Payment processPayment(Payment payment) throws PaymentException {
        try {
            // In a real application, this would integrate with a payment gateway
            validatePayment(payment);
            
            // Simulate payment processing
            payment.setStatus("COMPLETED");
            payment.setUpdatedAt(java.time.LocalDateTime.now());
            
            return payment;
        } catch (Exception e) {
            throw new PaymentException("Credit card payment failed", e);
        }
    }

    private void validatePayment(Payment payment) {
        if (payment.getAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (payment.getCurrency() == null || payment.getCurrency().isEmpty()) {
            throw new IllegalArgumentException("Currency is required");
        }
    }
} 