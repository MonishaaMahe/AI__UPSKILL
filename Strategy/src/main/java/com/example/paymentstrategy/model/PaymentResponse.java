package com.example.paymentstrategy.model;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {
    private String transactionId;
    private String status;
    private String message;
    private LocalDateTime timestamp;
    private String paymentType;
} 