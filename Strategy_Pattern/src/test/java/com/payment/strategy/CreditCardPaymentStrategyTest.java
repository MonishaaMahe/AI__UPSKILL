package com.payment.strategy;

import com.payment.model.Payment;
import com.payment.exception.PaymentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class CreditCardPaymentStrategyTest {
    private CreditCardPaymentStrategy strategy;
    private Payment payment;

    @BeforeEach
    void setUp() {
        strategy = new CreditCardPaymentStrategy();
        payment = new Payment();
        payment.setAmount(new BigDecimal("100.00"));
        payment.setCurrency("USD");
        payment.setPaymentMethod("creditcard");
        payment.setUserId("user123");
        payment.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void processPayment_Success() throws PaymentException {
        Payment result = strategy.processPayment(payment);
        assertNotNull(result);
        assertEquals("COMPLETED", result.getStatus());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void processPayment_InvalidAmount() {
        payment.setAmount(new BigDecimal("-100.00"));
        assertThrows(PaymentException.class, () -> strategy.processPayment(payment));
    }

    @Test
    void processPayment_InvalidCurrency() {
        payment.setCurrency(null);
        assertThrows(PaymentException.class, () -> strategy.processPayment(payment));
    }
} 