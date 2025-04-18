package com.example.StartupSafari.controller;

import com.example.StartupSafari.model.Application;
import com.example.StartupSafari.model.CoFounderRequest;
import com.example.StartupSafari.model.User;
import com.example.StartupSafari.repository.UserRepository;
import com.example.StartupSafari.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String redirectToRegisterForm() {
        return "redirect:/users/register"; // redirect to controller handling form + model
    }

    @GetMapping("/founder-dashboard")
    public String founderDashboard(Model model, Authentication authentication) {
        addUserInfo(model, authentication);
        return "forward:/api/founder"; // Delegate rendering to FounderController
    }

    @GetMapping("/cofounder-dashboard")
    public String cofounderDashboard(Model model, Authentication authentication) {
        addUserInfo(model, authentication);
        return "cofounder-dashboard";
    }

    @GetMapping("/investor-dashboard")
    public String investorDashboard(Model model, Authentication authentication) {
        addUserInfo(model, authentication);
        return "investor-dashboard";
    }

    private void addUserInfo(Model model, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("username", user.getName());
            model.addAttribute("role", user.getRole());
        } else {
            model.addAttribute("username", "Unknown User");
            model.addAttribute("role", "Unknown Role");
        }
    }

    @GetMapping("/founder/applications")
    public String viewApplications(Model model, Authentication authentication) {
        String email = authentication.getName(); // Get logged-in user's email
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            List<Application> applications = applicationService.getApplicationsForFounder(email);
            model.addAttribute("applications", applications);
        }

        return "founder-applications"; // Thymeleaf HTML page
    }
}