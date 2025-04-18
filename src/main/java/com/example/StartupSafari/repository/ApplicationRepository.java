package com.example.StartupSafari.repository;

import com.example.StartupSafari.model.Application;
import com.example.StartupSafari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByCofounder(User cofounder);
    List<Application> findByRequestFounder(User founder);
    List<Application> findByFounderEmail(String email);
}
