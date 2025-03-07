package com.example.StartupSafari.repository;

import com.example.StartupSafari.model.StartupIdea;
import com.example.StartupSafari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupIdeaRepository extends JpaRepository<StartupIdea , Long> {
    List<StartupIdea> findByInvestor(User investor);
}
