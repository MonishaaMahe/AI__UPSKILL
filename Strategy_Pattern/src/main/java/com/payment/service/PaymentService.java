package com.payment.service;

import com.payment.model.Payment;
import com.payment.strategy.PaymentStrategy;
import com.payment.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentService {
    private final Map<String, PaymentStrategy> paymentStrategies;

    @Autowired
    public PaymentService(Map<String, PaymentStrategy> paymentStrategies) {
        this.paymentStrategies = paymentStrategies;
    }

    public Payment processPayment(Payment payment) throws PaymentException {
        PaymentStrategy strategy = paymentStrategies.get(payment.getPaymentMethod().toLowerCase());
        if (strategy == null) {
            throw new PaymentException("Unsupported payment method: " + payment.getPaymentMethod());
        }
        return strategy.processPayment(payment);
    }
} 