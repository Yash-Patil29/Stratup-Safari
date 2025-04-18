package com.example.StartupSafari.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Application")
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getFounderEmail() {
        return founderEmail;
    }

    public void setFounderEmail(String founderEmail) {
        this.founderEmail = founderEmail;
    }

    private String founderEmail;

    @ManyToOne
    @JoinColumn(name = "cofounder_id", nullable = false)
    private User cofounder;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private CoFounderRequest request;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCofounder() {
        return cofounder;
    }

    public void setCofounder(User cofounder) {
        this.cofounder = cofounder;
    }

    public CoFounderRequest getRequest() {
        return request;
    }

    public void setRequest(CoFounderRequest request) {
        this.request = request;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
