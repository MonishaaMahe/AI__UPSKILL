package com.example.vitalsign.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class VitalSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private int heartRate;
    private String bloodPressure;
    private double temperature;
    private LocalDateTime timestamp;
} 