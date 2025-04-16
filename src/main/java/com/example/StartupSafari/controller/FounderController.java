package com.example.StartupSafari.controller;

import com.example.StartupSafari.model.*;
import com.example.StartupSafari.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/founder")
public class FounderController {

    private final CoFounderRequestService coFounderRequestService;
    private final ApplicationService applicationService;
    private final StartupIdeaService startupIdeaService;

    @Autowired
    public FounderController(CoFounderRequestService coFounderRequestService,
                             ApplicationService applicationService,
                             StartupIdeaService startupIdeaService) {
        this.coFounderRequestService = coFounderRequestService;
        this.applicationService = applicationService;
        this.startupIdeaService = startupIdeaService;
    }

    // ✅ Post Co-founder requirement
    @PostMapping("/requests/{founderId}")
    public ResponseEntity<CoFounderRequest> postRequest(
            @PathVariable Long founderId,
            @RequestBody CoFounderRequest request) {
        return ResponseEntity.ok(coFounderRequestService.createRequest(founderId, request));
    }

    // ✅ View posted requests by founder
    @GetMapping("/requests/{email}")
    public ResponseEntity<List<CoFounderRequest>> getMyRequests(@PathVariable String email) {
        return ResponseEntity.ok(coFounderRequestService.getRequestsByFounder(email));
    }

    // ✅ View applications received for your requests
    @GetMapping("/applications/{founderId}")
    public ResponseEntity<List<Application>> viewApplications(@PathVariable Long founderId) {
        return ResponseEntity.ok(applicationService.getApplicationsForFounder(founderId));
    }

    // ✅ Update application status
    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestBody ApplicationStatusRequest statusRequest) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, statusRequest.getStatus()));
    }

    // ✅ Submit idea to investor
    @PostMapping("/ideas")
    public ResponseEntity<StartupIdea> submitIdea(@RequestBody StartupIdeaRequest request) {
        StartupIdea idea = startupIdeaService.submitIdea(
                request.getFounderId(), request.getInvestorId(), request.getTitle(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(idea);
    }
}