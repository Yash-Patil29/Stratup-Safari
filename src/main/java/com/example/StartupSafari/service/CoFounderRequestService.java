package com.example.StartupSafari.service;

import com.example.StartupSafari.model.CoFounderRequest;
import com.example.StartupSafari.model.User;
import com.example.StartupSafari.repository.CoFounderRequestRepository;
import com.example.StartupSafari.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CoFounderRequestService {

    private final CoFounderRequestRepository coFounderRequestRepository;
    private final UserRepository userRepository;

    @Autowired
    public CoFounderRequestService(CoFounderRequestRepository coFounderRequestRepository,
                                   UserRepository userRepository) {
        this.coFounderRequestRepository = coFounderRequestRepository;
        this.userRepository = userRepository;
    }

    //Founder posts a requirement
    public CoFounderRequest createRequest(Long founderId, CoFounderRequest request) {
        User founder = userRepository.findById(founderId)
                .orElseThrow(() -> new RuntimeException("Founder not found"));
        request.setFounder(founder);
        return coFounderRequestRepository.save(request);
    }

    //Get all co-founder requests
    public List<CoFounderRequest> getAllRequests() {
        return coFounderRequestRepository.findAll();
    }

    //Get requests posted by a specific founder
    public List<CoFounderRequest> getRequestsByFounder(String email) {
        return coFounderRequestRepository.findByFounderEmail(email);
    }

}
