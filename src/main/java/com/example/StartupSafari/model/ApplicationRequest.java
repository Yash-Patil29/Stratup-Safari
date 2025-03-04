package com.example.StartupSafari.model;

public class ApplicationRequest {
    private Long cofounderId;
    private Long requestId;
    private String coverLetter;

    public Long getCofounderId() {
        return cofounderId;
    }

    public void setCofounderId(Long cofounderId) {
        this.cofounderId = cofounderId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
