package com.example.StartupSafari.service;

import com.example.StartupSafari.dto.ApplicationDTO;
import com.example.StartupSafari.model.*;
import com.example.StartupSafari.repository.ApplicationRepository;
import com.example.StartupSafari.repository.CoFounderRequestRepository;
import com.example.StartupSafari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }

//    public List<ApplicationDTO> getApplicationDTOsForFounder(Long founderId) {
//        User founder = userRepository.findById(founderId)
//                .orElseThrow(() -> new RuntimeException("Founder not found"));
//
//        List<Application> applications = applicationRepository.findByRequestFounder(founder);
//
//        return applications.stream().map(app -> {
//            ApplicationDTO dto = new ApplicationDTO();
//            dto.setId(app.getId());
//            dto.setCofounderName(app.getCofounder().getName());
//            dto.setCofounderEmail(app.getCofounder().getEmail());
//            dto.setCoverLetter(app.getCoverLetter());
//            dto.setStatus(app.getStatus().name());
//            return dto;
//        }).toList();
//    }

    public List<ApplicationDTO> getAllApplicationsForFounder(String email) {
        List<Application> applications = applicationRepository.findByFounderEmail(email);

        return applications.stream()
                .map(application -> {
                    ApplicationDTO dto = new ApplicationDTO();
                    dto.setId(application.getId());
                    dto.setCofounderName(application.getCofounder().getName());
                    dto.setCofounderEmail(application.getCofounder().getEmail());
                    dto.setCoverLetter(application.getCoverLetter());
                    dto.setStatus(application.getStatus().toString()); // convert enum to string
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<Application> getApplicationsForFounder(String email) {
        User founder = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByRequestFounder(founder);
    }
    public List<Application> getApplicationEntitiesForFounder(Long founderId) {
        User founder = userRepository.findById(founderId)
                .orElseThrow(() -> new RuntimeException("Founder not found"));

        return applicationRepository.findByRequestFounder(founder);
    }
}
