package com.example.StartupSafari.controller;

import com.example.StartupSafari.model.Application;
import com.example.StartupSafari.model.ApplicationRequest;
import com.example.StartupSafari.model.ApplicationStatus;
import com.example.StartupSafari.model.ApplicationStatusRequest;
import com.example.StartupSafari.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {


    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> applyForCoFounder(@RequestBody ApplicationRequest request) {
        Application application = applicationService.applyForRequest(
                request.getCofounderId(), request.getRequestId(), request.getCoverLetter()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

//    @GetMapping("/cofounder/{cofounderId}")
//    public ResponseEntity<List<Application>> getApplicationsByCofounder(@PathVariable Long cofounderId) {
//        return ResponseEntity.ok(applicationService.getApplicationsByCofounder(cofounderId));
//    }

    @GetMapping("/founder/{founderId}")
    public ResponseEntity<List<Application>> getApplicationsForFounder(@PathVariable Long founderId) {
        List<Application> applications = applicationService.getApplicationsForFounder(founderId);
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/applications/{id}/status")
    public String updateApplicationStatus(@PathVariable Long id,
                                          @RequestParam("status") String status) {
        applicationService.updateApplicationStatus(id, ApplicationStatus.valueOf(status));
        return "redirect:/founder/dashboard";  // Adjust path as per your routing
    }

//    @PutMapping("/{applicationId}/status")
//    public ResponseEntity<Application> updateApplicationStatus(
//            @PathVariable Long applicationId,
//            @RequestBody ApplicationStatusRequest statusRequest) {  // Accept JSON body
//        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, statusRequest.getStatus()));
//    }
}
