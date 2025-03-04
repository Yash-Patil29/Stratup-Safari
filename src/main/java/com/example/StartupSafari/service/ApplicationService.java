package com.example.StartupSafari.service;

import com.example.StartupSafari.model.Application;
import com.example.StartupSafari.model.ApplicationStatus;
import com.example.StartupSafari.model.CoFounderRequest;
import com.example.StartupSafari.model.User;
import com.example.StartupSafari.repository.ApplicationRepository;
import com.example.StartupSafari.repository.CoFounderRequestRepository;
import com.example.StartupSafari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    private final CoFounderRequestRepository coFounderRequestRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, CoFounderRequestRepository coFounderRequestRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.coFounderRequestRepository = coFounderRequestRepository;
    }

    public Application applyForRequest(Long cofounderId, Long requestId, String coverLetter) {
        User cofounder = userRepository.findById(cofounderId)
                .orElseThrow(() -> new RuntimeException("Cofounder not found"));

        CoFounderRequest request = coFounderRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Co-Founder Request not found"));

        Application application = new Application();
        application.setCofounder(cofounder);
        application.setRequest(request);
        application.setCoverLetter(coverLetter);
        application.setStatus(ApplicationStatus.PENDING);

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByCofounder(Long cofounderId) {
        User cofounder = userRepository.findById(cofounderId)
                .orElseThrow(() -> new RuntimeException("Cofounder not found"));

        return applicationRepository.findByCofounder(cofounder);
    }

    public List<Application> getApplicationsForFounder(Long founderId) {
        User founder = userRepository.findById(founderId)
                .orElseThrow(() -> new RuntimeException("Founder not found"));

        return applicationRepository.findByRequestFounder(founder);
    }

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
