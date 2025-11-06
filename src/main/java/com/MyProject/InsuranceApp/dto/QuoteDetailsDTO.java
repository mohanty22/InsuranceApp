package com.MyProject.InsuranceApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class QuoteDetailsDTO {

    @NotBlank(message = "PAC flag is required")
    private String pacAprflg;

    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Invalid PAN format")
    private String pan_no;

    private PlanDetailsDTO planDetails;

    // Default constructor
    public QuoteDetailsDTO() {
    }

    // Parameterized constructor
    public QuoteDetailsDTO(String pacAprflg, String pan_no, PlanDetailsDTO planDetails) {
        this.pacAprflg = pacAprflg;
        this.pan_no = pan_no;
        this.planDetails = planDetails;
    }

    // Getters and setters
    public String getPacAprflg() {
        return pacAprflg;
    }

    public void setPacAprflg(String pacAprflg) {
        this.pacAprflg = pacAprflg;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public PlanDetailsDTO getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(PlanDetailsDTO planDetails) {
        this.planDetails = planDetails;
    }

    @Override
    public String toString() {
        return "QuoteDetailsDTO{" +
                "pacAprflg='" + pacAprflg + '\'' +
                ", pan_no='" + pan_no + '\'' +
                ", planDetails=" + planDetails +
                '}';
    }
}
