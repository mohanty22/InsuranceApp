package com.MyProject.InsuranceApp.dto.ProductsDTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PremiumCalculationRequestDTO {

    @NotBlank(message = "Product code is required")
    private String productCode;

    @NotNull(message = "Sum Assured is required")
    @Min(value = 100000, message = "Sum Assured must be at least 100,000")
    private Long sumAssured;

    @NotNull(message = "Policy Paying Term (PPT) is required")
    @Min(value = 5, message = "PPT must be at least 5 years")
    private Integer policyPayingTerm;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Annual income is required")
    private Long annualIncome;

    @NotBlank(message = "Occupation is required")
    private String occupation;

    @NotBlank(message = "Risk Category is required")
    private String riskCategory;

    @NotBlank(message = "Frequency is required (ANNUAL, MONTHLY, HALF_YEARLY)")
    private String frequency;

    private List<String> riderCodes;

    // Getters and Setters
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public Long getSumAssured() { return sumAssured; }
    public void setSumAssured(Long sumAssured) { this.sumAssured = sumAssured; }
    public Integer getPolicyPayingTerm() { return policyPayingTerm; }
    public void setPolicyPayingTerm(Integer policyPayingTerm) { this.policyPayingTerm = policyPayingTerm; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Long getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Long annualIncome) { this.annualIncome = annualIncome; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public List<String> getRiderCodes() { return riderCodes; }
    public void setRiderCodes(List<String> riderCodes) { this.riderCodes = riderCodes; }
}