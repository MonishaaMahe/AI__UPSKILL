package com.lms.progressservice.controller;

import com.lms.progressservice.entity.Certificate;
import com.lms.progressservice.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService certificateService;

    @PostMapping("/issue")
    public CompletableFuture<ResponseEntity<Certificate>> issueCertificate(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam String certificateUrl) {
        return certificateService.issueCertificate(studentId, courseId, certificateUrl)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getCertificatesByStudent(@RequestParam Long studentId) {
        return ResponseEntity.ok(certificateService.getCertificatesByStudent(studentId));
    }
} 