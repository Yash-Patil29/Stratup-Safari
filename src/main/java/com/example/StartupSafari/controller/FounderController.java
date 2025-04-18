package com.example.StartupSafari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.StartupSafari.dto.ApplicationDTO;
import com.example.StartupSafari.model.*;
import com.example.StartupSafari.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/founder")
public class FounderController {

    private final CoFounderRequestService coFounderRequestService;
    private final ApplicationService applicationService;
    private final StartupIdeaService startupIdeaService;
    private InvestorService investorService;
    private final UserService userService;

    @Autowired
    public FounderController(CoFounderRequestService coFounderRequestService,
                             ApplicationService applicationService,
                             StartupIdeaService startupIdeaService,
                             InvestorService investorService,
                             UserService userService) {
        this.coFounderRequestService = coFounderRequestService;
        this.applicationService = applicationService;
        this.startupIdeaService = startupIdeaService;
        this.investorService = investorService;
        this.userService=userService;
    }

    // ✅ Post co-founder request
    @PostMapping("/requests/{founderId}")
    public String postRequest(@PathVariable Long founderId, @ModelAttribute CoFounderRequest request) {
        coFounderRequestService.createRequest(founderId, request);
        return "redirect:/founder-dashboard";
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
    @PostMapping("/applications/{applicationId}/status")
    public String updateApplicationStatusForm(
            @PathVariable Long applicationId,
            @RequestParam("status") String statusStr) {

        ApplicationStatus status = ApplicationStatus.valueOf(statusStr.toUpperCase());
        applicationService.updateApplicationStatus(applicationId, status);
        return "redirect:/founder-dashboard";
    }

    // ✅ Submit idea to investor
    @PostMapping("/ideas")
    public String submitIdea(@ModelAttribute StartupIdeaRequest request) {
        startupIdeaService.submitIdea(
                request.getFounderId(), request.getInvestorId(),
                request.getTitle(), request.getDescription()
        );
        return "redirect:/founder-dashboard"; // back to dashboard
    }


    @GetMapping
    public String showFounderDashboard(Model model, Principal principal) {
        String email = principal.getName();
        String username = email.substring(0, email.indexOf('@'));
        model.addAttribute("username", username);

        List<ApplicationDTO> applications = applicationService.getAllApplicationsForFounder(email);
        List<User> investors = investorService.getAllInvestors();
        Long founderId = userService.getUserIdByEmail(email);

        model.addAttribute("applications", applications);
        model.addAttribute("investors", investors);
        model.addAttribute("founderId", founderId);

        // ✅ THIS IS THE FIX: Add coFounderRequest object to model
        model.addAttribute("CoFounderRequest", new CoFounderRequest());

        return "founder-dashboard";
    }
}