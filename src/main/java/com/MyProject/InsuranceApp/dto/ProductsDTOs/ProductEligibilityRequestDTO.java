package com.MyProject.InsuranceApp.dto.ProductsDTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductEligibilityRequestDTO {

    @Min(value = 18, message = "Age must be at least 18")
    @NotNull(message = "Age is required")
    private Integer age;

    @Min(value = 100000, message = "Annual income must be at least 100,000")
    @NotNull(message = "Annual income is required")
    private Long annualIncome;

    @NotBlank(message = "Occupation is required")
    @Pattern(regexp = "^(salaried|self employed|housewife|student|unemployed|retired)$", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Occupation must be one of: salaried, self employed, housewife, student, unemployed, retired")
    private String occupation;

    @NotBlank(message = "Risk Category is required")
    private String riskCategory;

    // Getters and Setters
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Long getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Long annualIncome) { this.annualIncome = annualIncome; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }
}