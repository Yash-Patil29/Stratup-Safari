package com.example.StartupSafari.repository;

import com.example.StartupSafari.model.CoFounderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoFounderRequestRepository extends JpaRepository<CoFounderRequest,Long> {

    List<CoFounderRequest>findByFounderEmail(String email);
}
