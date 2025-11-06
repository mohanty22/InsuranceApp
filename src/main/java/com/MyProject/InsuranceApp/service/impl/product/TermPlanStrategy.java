package com.MyProject.InsuranceApp.service.impl.product;


import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationRequestDTO;
import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationResponseDTO;
import com.MyProject.InsuranceApp.service.premium.PremiumCalculationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TermPlanStrategy implements PremiumCalculationStrategy {

    public static final String TERM_PLAN_CODE = "TERM_LIFE";
    private static final BigDecimal BASE_RATE_PER_LAKH_PER_YEAR = new BigDecimal("0.0005");

    @Override
    public String getProductCode() {
        return TERM_PLAN_CODE;
    }

    @Override
    public PremiumCalculationResponseDTO calculate(PremiumCalculationRequestDTO request) {
        // 1. Inputs
        Long sumAssured = request.getSumAssured();
        int age = request.getAge();
        String riskCategory = request.getRiskCategory();
        String occupation = request.getOccupation();
        String frequency = request.getFrequency();

        PremiumCalculationResponseDTO response = new PremiumCalculationResponseDTO();

        // --- COMPLEX LOGIC STARTS HERE ---

        // 2. Base Premium Calculation: SA * Rate * Age Factor
        BigDecimal baseRateFactor = new BigDecimal(age).divide(new BigDecimal("20"), 2, RoundingMode.HALF_UP);
        BigDecimal basePremium = new BigDecimal(sumAssured)
                .multiply(BASE_RATE_PER_LAKH_PER_YEAR)
                .multiply(baseRateFactor)
                .setScale(0, RoundingMode.HALF_UP);

        // 3. Risk Loading (based on Health/Lifestyle)
        BigDecimal riskFactor = switch (riskCategory) {
            case "HIGH_RISK" -> new BigDecimal("1.30");
            case "STANDARD" -> new BigDecimal("1.00");
            case "SUPER_PREFERRED" -> new BigDecimal("0.90");
            default -> new BigDecimal("1.00");
        };
        BigDecimal riskLoading = basePremium.multiply(riskFactor.subtract(BigDecimal.ONE)).setScale(0, RoundingMode.HALF_UP).max(BigDecimal.ZERO);


        // 4. Occupation Loading (based on Income Stability/Hazard)
        BigDecimal occupationFactor = switch (occupation.toLowerCase()) {
            case "salaried" -> new BigDecimal("1.00");
            case "retired" -> new BigDecimal("1.10");
            case "self employed" -> new BigDecimal("1.15");
            case "housewife", "student" -> new BigDecimal("1.20");
            case "unemployed" -> new BigDecimal("1.50");
            default -> new BigDecimal("1.00");
        };

        BigDecimal premiumAfterRisk = basePremium.add(riskLoading);
        BigDecimal occupationLoading = premiumAfterRisk.multiply(occupationFactor.subtract(BigDecimal.ONE)).setScale(0, RoundingMode.HALF_UP).max(BigDecimal.ZERO);

        // 5. Rider Premium (Cost based on number of riders)
        BigDecimal riderPremium = new BigDecimal(request.getRiderCodes() != null ? request.getRiderCodes().size() : 0)
                .multiply(new BigDecimal("500"));

        // 6. Discount (Conditional Logic: High Sum Assured Discount)
        BigDecimal discount = BigDecimal.ZERO;
        if (sumAssured > 50_00_000) {
            discount = basePremium.multiply(new BigDecimal("0.02")).setScale(0, RoundingMode.HALF_UP);
        }

        // 7. Total Annual Premium
        BigDecimal totalAnnualPremium = premiumAfterRisk.add(occupationLoading).add(riderPremium).subtract(discount).max(BigDecimal.ZERO);

        // 8. Modal Factor (based on frequency)
        BigDecimal modalFactor = switch (frequency) {
            case "ANNUAL" -> BigDecimal.ONE;
            case "HALF_YEARLY" -> new BigDecimal("0.505");
            case "MONTHLY" -> new BigDecimal("0.085");
            default -> BigDecimal.ONE;
        };

        BigDecimal modalPremium = totalAnnualPremium.multiply(modalFactor).setScale(0, RoundingMode.HALF_UP);

        // --- MAPPING RESPONSE ---
        response.setPlanOption(request.getProductCode());
        response.setBasePremium(basePremium);
        response.setRiskLoading(riskLoading);
        response.setOccupationLoading(occupationLoading);
        response.setRiderPremium(riderPremium);
        response.setFinalDiscount(discount);
        response.setTotalAnnualPremium(totalAnnualPremium);
        response.setModalPremium(modalPremium);
        response.setCalculatedByStrategy(this.getClass().getSimpleName());

        return response;
    }
}
