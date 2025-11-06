package com.MyProject.InsuranceApp.config;

import java.util.List;

public class ProductMetadata {
    private String code;
    private String name;
    private String category;
    private long minSumAssured;
    private long maxSumAssured;
    private List<RiderMetadata> availableRiders;

    public ProductMetadata(String code, String name, String category, long minSumAssured, long maxSumAssured, List<RiderMetadata> availableRiders) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.minSumAssured = minSumAssured;
        this.maxSumAssured = maxSumAssured;
        this.availableRiders = availableRiders;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public long getMinSumAssured() { return minSumAssured; }
    public void setMinSumAssured(long minSumAssured) { this.minSumAssured = minSumAssured; }
    public long getMaxSumAssured() { return maxSumAssured; }
    public void setMaxSumAssured(long maxSumAssured) { this.maxSumAssured = maxSumAssured; }
    public List<RiderMetadata> getAvailableRiders() { return availableRiders; }
    public void setAvailableRiders(List<RiderMetadata> availableRiders) { this.availableRiders = availableRiders; }
}