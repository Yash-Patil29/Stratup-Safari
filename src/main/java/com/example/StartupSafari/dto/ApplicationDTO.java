package com.example.StartupSafari.dto;

import lombok.Data;

@Data
public class ApplicationDTO {
    private Long id;
    private String cofounderName;
    private String cofounderEmail;
    private String coverLetter;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCofounderName() {
        return cofounderName;
    }

    public void setCofounderName(String cofounderName) {
        this.cofounderName = cofounderName;
    }

    public String getCofounderEmail() {
        return cofounderEmail;
    }

    public void setCofounderEmail(String cofounderEmail) {
        this.cofounderEmail = cofounderEmail;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
