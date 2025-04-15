package com.example.StartupSafari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String redirectToRegisterForm() {
        return "redirect:/users/register"; // redirect to controller handling form + model
    }

    @GetMapping("/founder-dashboard")
    public String founderDashboard() {
        return "founder-dashboard";
    }

    @GetMapping("/cofounder-dashboard")
    public String cofounderDashboard() {
        return "cofounder-dashboard";
    }

    @GetMapping("/investor-dashboard")
    public String investorDashboard() {
        return "investor-dashboard";
    }
}