package com.MyProject.InsuranceApp.dto.ProductsDTOs;

import java.math.BigDecimal;
import java.util.List;

public class PremiumCalculationResponseDTO {
    private String planOption;
    private BigDecimal basePremium;
    private BigDecimal riskLoading;
    private BigDecimal occupationLoading;
    private BigDecimal riderPremium;
    private BigDecimal finalDiscount;
    private BigDecimal totalAnnualPremium;
    private BigDecimal modalPremium;
    private String calculatedByStrategy;

    // Getters and Setters
    public String getPlanOption() { return planOption; }
    public void setPlanOption(String planOption) { this.planOption = planOption; }
    public BigDecimal getBasePremium() { return basePremium; }
    public void setBasePremium(BigDecimal basePremium) { this.basePremium = basePremium; }
    public BigDecimal getRiskLoading() { return riskLoading; }
    public void setRiskLoading(BigDecimal riskLoading) { this.riskLoading = riskLoading; }
    public BigDecimal getOccupationLoading() { return occupationLoading; }
    public void setOccupationLoading(BigDecimal occupationLoading) { this.occupationLoading = occupationLoading; }
    public BigDecimal getRiderPremium() { return riderPremium; }
    public void setRiderPremium(BigDecimal riderPremium) { this.riderPremium = riderPremium; }
    public BigDecimal getFinalDiscount() { return finalDiscount; }
    public void setFinalDiscount(BigDecimal finalDiscount) { this.finalDiscount = finalDiscount; }
    public BigDecimal getTotalAnnualPremium() { return totalAnnualPremium; }
    public void setTotalAnnualPremium(BigDecimal totalAnnualPremium) { this.totalAnnualPremium = totalAnnualPremium; }
    public BigDecimal getModalPremium() { return modalPremium; }
    public void setModalPremium(BigDecimal modalPremium) { this.modalPremium = modalPremium; }
    public String getCalculatedByStrategy() { return calculatedByStrategy; }
    public void setCalculatedByStrategy(String calculatedByStrategy) { this.calculatedByStrategy = calculatedByStrategy; }
}
