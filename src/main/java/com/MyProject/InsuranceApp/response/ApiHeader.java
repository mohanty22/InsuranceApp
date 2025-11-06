package com.MyProject.InsuranceApp.response;

import java.time.LocalDateTime;

public class ApiHeader {
    private String status;
    private String message;
    private String actionId;
    private LocalDateTime timestamp;

    public ApiHeader() {}

    public ApiHeader(String status, String message, String actionId) {
        this.status = status;
        this.message = message;
        this.actionId = actionId;
        this.timestamp = LocalDateTime.now();
    }


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getActionId() { return actionId; }
    public void setActionId(String actionId) { this.actionId = actionId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}