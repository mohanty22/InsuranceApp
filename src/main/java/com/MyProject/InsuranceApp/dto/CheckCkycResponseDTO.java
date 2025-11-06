package com.MyProject.InsuranceApp.dto;

public class CheckCkycResponseDTO {
    private String message;
    private String otp;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public CheckCkycResponseDTO(String message, String otp, String sessionId) {
        this.message = message;
        this.otp = otp;
        this.sessionId = sessionId;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}