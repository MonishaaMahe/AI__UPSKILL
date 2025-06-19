package com.example.vitalsign.repository;

import com.example.vitalsign.model.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
} 