package com.lms.progressservice.service;

import com.lms.progressservice.entity.Certificate;
import com.lms.progressservice.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;

    @Async
    @Transactional
    public CompletableFuture<Certificate> issueCertificate(Long studentId, Long courseId, String certificateUrl) {
        Certificate certificate = new Certificate();
        certificate.setStudentId(studentId);
        certificate.setCourseId(courseId);
        certificate.setIssuedAt(LocalDateTime.now());
        certificate.setCertificateUrl(certificateUrl);
        return CompletableFuture.completedFuture(certificateRepository.save(certificate));
    }

    public List<Certificate> getCertificatesByStudent(Long studentId) {
        return certificateRepository.findByStudentId(studentId);
    }
} 