package com.MyProject.InsuranceApp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CkycTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appnum; // Key to map back to the Application
    private String sessionId; // Optional: To correlate check-ckyc and fetch-ckyc-details attempts
    private String serviceName; // "CHECK_CKYC" or "FETCH_CKYC_DETAILS"
    private LocalDateTime timestamp = LocalDateTime.now();
    private String status; // SUCCESS, FAILURE, NO_MATCH, UNAUTHORIZED

    @Lob
    private String requestPayload;

    @Lob
    private String responsePayload;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAppnum() { return appnum; }
    public void setAppnum(String appnum) { this.appnum = appnum; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRequestPayload() { return requestPayload; }
    public void setRequestPayload(String requestPayload) { this.requestPayload = requestPayload; }
    public String getResponsePayload() { return responsePayload; }
    public void setResponsePayload(String responsePayload) { this.responsePayload = responsePayload; }

    public CkycTransactionLog() {}
}