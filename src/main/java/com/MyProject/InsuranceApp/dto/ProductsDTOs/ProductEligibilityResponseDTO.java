package com.MyProject.InsuranceApp.dto.ProductsDTOs;

public class ProductEligibilityResponseDTO {
    private String productCode;
    private String productName;
    private String description;
    private long minSumAssured;
    private long maxSumAssured;

    public ProductEligibilityResponseDTO(String productCode, String productName, String description, long minSumAssured, long maxSumAssured) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.minSumAssured = minSumAssured;
        this.maxSumAssured = maxSumAssured;
    }

    // Getters and Setters
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public long getMinSumAssured() { return minSumAssured; }
    public void setMinSumAssured(long minSumAssured) { this.minSumAssured = minSumAssured; }
    public long getMaxSumAssured() { return maxSumAssured; }
    public void setMaxSumAssured(long maxSumAssured) { this.maxSumAssured = maxSumAssured; }
}
