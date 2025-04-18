package com.example.StartupSafari.service;

import com.example.StartupSafari.model.IdeaStatus;
import com.example.StartupSafari.model.StartupIdea;
import com.example.StartupSafari.model.User;
import com.example.StartupSafari.repository.StartupIdeaRepository;
import com.example.StartupSafari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartupIdeaService {

    private final StartupIdeaRepository startupIdeaRepository;
    private final UserRepository userRepository;

    @Autowired
    public StartupIdeaService(StartupIdeaRepository startupIdeaRepository, UserRepository userRepository) {
        this.startupIdeaRepository = startupIdeaRepository;
        this.userRepository = userRepository;
    }

    // Founder submits an idea to an investor
    public StartupIdea submitIdea(Long founderId, Long investorId, String title, String description) {
        User founder = userRepository.findById(founderId)
                .orElseThrow(() -> new RuntimeException("Founder not found"));

        User investor = userRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        StartupIdea idea = new StartupIdea();
        idea.setFounder(founder);
        idea.setInvestor(investor);
        idea.setTitle(title);
        idea.setDescription(description);
        idea.setStatus(IdeaStatus.PENDING);

        return startupIdeaRepository.save(idea);
    }

    // Investor views all ideas sent to them
    public List<StartupIdea> getIdeasForInvestor(Long investorId) {
        User investor = userRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        return startupIdeaRepository.findByInvestor(investor);
    }

    public List<StartupIdea> getIdeasByInvestorAndStatus(Long investorId, IdeaStatus status) {
        User investor = userRepository.findById(investorId)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        return startupIdeaRepository.findByInvestorAndStatus(investor, status);
    }
}
