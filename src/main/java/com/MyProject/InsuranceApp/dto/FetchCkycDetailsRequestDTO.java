package com.MyProject.InsuranceApp.dto;

import jakarta.validation.constraints.NotBlank;

public class FetchCkycDetailsRequestDTO {
    @NotBlank(message = "Session ID is required")
    private String sessionId;

    @NotBlank(message = "PAN number is required")
    private String panNumber;

    @NotBlank(message = "OTP is required")
    private String otp;

    // Getters and Setters
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
