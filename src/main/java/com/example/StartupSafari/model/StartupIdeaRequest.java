package com.example.StartupSafari.model;

public class StartupIdeaRequest {
    private Long founderId;
    private Long investorId;
    private String title;
    private String description;

    public Long getFounderId() {
        return founderId;
    }

    public void setFounderId(Long founderId) {
        this.founderId = founderId;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
