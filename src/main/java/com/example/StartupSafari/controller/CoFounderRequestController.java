package com.example.StartupSafari.controller;

import com.example.StartupSafari.model.CoFounderRequest;
import com.example.StartupSafari.service.CoFounderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cofounder-requests")
public class CoFounderRequestController {

    private final CoFounderRequestService coFounderRequestService;

    @Autowired
    public CoFounderRequestController(CoFounderRequestService coFounderRequestService) {
        this.coFounderRequestService = coFounderRequestService;
    }

//    // Founder posts a co-founder requirement
//    @PostMapping("/{founderId}")
//    public ResponseEntity<CoFounderRequest> createRequest(
//            @PathVariable Long founderId,
//            @RequestBody CoFounderRequest request) {
//        return ResponseEntity.ok(coFounderRequestService.createRequest(founderId, request));
//    }

    // Co-founders can view all requests
    @GetMapping
    public ResponseEntity<List<CoFounderRequest>> getAllRequests() {
        return ResponseEntity.ok(coFounderRequestService.getAllRequests());
    }

    // Get requests posted by a specific founder using email
//    @GetMapping("/founder/{email}")
//    public ResponseEntity<List<CoFounderRequest>> getRequestsByFounder(@PathVariable String email) {
//        return ResponseEntity.ok(coFounderRequestService.getRequestsByFounderEmail(email));
//    }
}