package com.MyProject.InsuranceApp.service.premium;


import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationRequestDTO;
import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationResponseDTO;

public interface PremiumCalculationStrategy {
    String getProductCode();

    PremiumCalculationResponseDTO calculate(PremiumCalculationRequestDTO request);
}