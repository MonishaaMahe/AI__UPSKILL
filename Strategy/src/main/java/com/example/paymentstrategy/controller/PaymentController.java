package com.example.paymentstrategy.controller;

import com.example.paymentstrategy.model.PaymentRequest;
import com.example.paymentstrategy.model.PaymentResponse;
import com.example.paymentstrategy.strategy.PaymentContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    private final PaymentContext paymentContext;

    public PaymentController(PaymentContext paymentContext) {
        this.paymentContext = paymentContext;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentContext.processPayment(request);
        return ResponseEntity.ok(response);
    }
} 