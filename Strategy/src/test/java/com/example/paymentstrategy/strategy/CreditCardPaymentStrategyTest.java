package com.example.paymentstrategy.strategy;

import com.example.paymentstrategy.model.PaymentRequest;
import com.example.paymentstrategy.model.PaymentResponse;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardPaymentStrategyTest {

    private final CreditCardPaymentStrategy strategy = new CreditCardPaymentStrategy();

    @Test
    void testProcessPayment() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setCardNumber("4111111111111111");
        request.setCardHolderName("John Doe");
        request.setExpiryDate("12/25");
        request.setCvv("123");
        request.setAmount(new BigDecimal("100.00"));
        request.setCurrency("USD");
        request.setPaymentType("CREDIT_CARD");

        // Act
        PaymentResponse response = strategy.processPayment(request);

        // Assert
        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        assertEquals("CREDIT_CARD", response.getPaymentType());
        assertNotNull(response.getTransactionId());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void testGetStrategyName() {
        assertEquals("CREDIT_CARD", strategy.getStrategyName());
    }
} 