package com.example.paymentstrategy.strategy;

import com.example.paymentstrategy.model.PaymentRequest;
import com.example.paymentstrategy.model.PaymentResponse;

public interface PaymentStrategy {
    PaymentResponse processPayment(PaymentRequest request);
    String getStrategyName();
} 