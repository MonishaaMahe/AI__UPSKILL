package com.example.paymentstrategy.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private BigDecimal amount;
    private String currency;
    private String paymentType;
} 