package com.example.paymentstrategy.strategy;

import com.example.paymentstrategy.model.PaymentRequest;
import com.example.paymentstrategy.model.PaymentResponse;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

@Service
public class PaymentContext {
    private final Map<String, PaymentStrategy> strategies;

    public PaymentContext(CreditCardPaymentStrategy creditCardPaymentStrategy) {
        strategies = new HashMap<>();
        strategies.put("CREDIT_CARD", creditCardPaymentStrategy);
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentStrategy strategy = strategies.get(request.getPaymentType());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + request.getPaymentType());
        }
        return strategy.processPayment(request);
    }
} 