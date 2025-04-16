package com.example.StartupSafari.repository;

import com.example.StartupSafari.model.IdeaStatus;
import com.example.StartupSafari.model.StartupIdea;
import com.example.StartupSafari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartupIdeaRepository extends JpaRepository<StartupIdea, Long> {

    List<StartupIdea> findByInvestor(User investor);

    List<StartupIdea> findByInvestorAndStatus(User investor, IdeaStatus status);  // Add this query method
}
