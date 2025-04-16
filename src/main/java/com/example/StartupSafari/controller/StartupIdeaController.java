package com.example.StartupSafari.controller;


import com.example.StartupSafari.model.StartupIdea;
import com.example.StartupSafari.model.StartupIdeaRequest;
import com.example.StartupSafari.service.StartupIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/startup-ideas")
public class StartupIdeaController {

    private final StartupIdeaService startupIdeaService;

    @Autowired
    public StartupIdeaController(StartupIdeaService startupIdeaService) {
        this.startupIdeaService = startupIdeaService;
    }

//    // Founder submits an idea to an investor
//    @PostMapping
//    public ResponseEntity<StartupIdea> submitIdea(@RequestBody StartupIdeaRequest request) {
//        StartupIdea idea = startupIdeaService.submitIdea(
//                request.getFounderId(), request.getInvestorId(), request.getTitle(), request.getDescription()
//        );
//        return ResponseEntity.status(HttpStatus.CREATED).body(idea);
//    }

    // Investor views all ideas sent to them
    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<StartupIdea>> getIdeasForInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(startupIdeaService.getIdeasForInvestor(investorId));
    }
}
