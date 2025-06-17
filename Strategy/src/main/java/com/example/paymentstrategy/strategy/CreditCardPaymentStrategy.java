package com.example.paymentstrategy.strategy;

import com.example.paymentstrategy.model.PaymentRequest;
import com.example.paymentstrategy.model.PaymentResponse;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        // In a real application, this would integrate with a payment gateway
        // For demonstration, we'll simulate a successful payment
        
        return PaymentResponse.builder()
                .transactionId(UUID.randomUUID().toString())
                .status("SUCCESS")
                .message("Credit card payment processed successfully")
                .timestamp(LocalDateTime.now())
                .paymentType("CREDIT_CARD")
                .build();
    }

    @Override
    public String getStrategyName() {
        return "CREDIT_CARD";
    }
} 