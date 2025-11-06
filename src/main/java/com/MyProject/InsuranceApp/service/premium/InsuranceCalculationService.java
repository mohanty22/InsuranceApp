package com.MyProject.InsuranceApp.service.premium;

import com.MyProject.InsuranceApp.config.ProductCatalog;
import com.MyProject.InsuranceApp.config.ProductMetadata;

import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationRequestDTO;
import com.MyProject.InsuranceApp.dto.ProductsDTOs.PremiumCalculationResponseDTO;
import com.MyProject.InsuranceApp.dto.ProductsDTOs.ProductEligibilityRequestDTO;
import com.MyProject.InsuranceApp.dto.ProductsDTOs.ProductEligibilityResponseDTO;
import com.MyProject.InsuranceApp.service.premium.PremiumCalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InsuranceCalculationService {

    private final Map<String, PremiumCalculationStrategy> strategies;
    private final ProductCatalog productCatalog;

    public InsuranceCalculationService(List<PremiumCalculationStrategy> strategyList, ProductCatalog productCatalog) {
        // Collects all Strategy beans into a map keyed by their product code
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(PremiumCalculationStrategy::getProductCode, Function.identity()));
        this.productCatalog = productCatalog;
    }

    // --- API 1 Implementation: Product Eligibility (Business Filtering) ---
    public List<ProductEligibilityResponseDTO> findAvailableProducts(ProductEligibilityRequestDTO request) {

        List<ProductMetadata> allProducts = productCatalog.getAllProducts();

        return allProducts.stream()
                .filter(product -> {
                    // Rule 1: ULIP products are only for high-income/stable jobs
                    if (product.getCategory().equals("ULIP")) {
                        return request.getAnnualIncome() > 4000000 && request.getAge() < 55 &&
                                !request.getOccupation().equalsIgnoreCase("unemployed");
                    }
                    // Rule 2: Endowment is offered universally
                    if (product.getCategory().equals("ENDOWMENT")) {
                        return true;
                    }
                    // Rule 3: Term is offered to all working/retired, but not students/unemployed below 30
                    if (product.getCategory().equals("TERM")) {
                        String occupation = request.getOccupation().toLowerCase();
                        if (occupation.equals("student") || occupation.equals("unemployed")) {
                            return request.getAge() >= 30;
                        }
                        return true;
                    }
                    return false;
                })
                .map(product -> new ProductEligibilityResponseDTO(
                        product.getCode(),
                        product.getName(),
                        product.getCategory() + " Plan",
                        product.getMinSumAssured(),
                        product.getMaxSumAssured()
                ))
                .collect(Collectors.toList());
    }

    // --- API 2 Implementation: Premium Calculation (Strategy Pattern Context) ---
    public PremiumCalculationResponseDTO calculatePremium(PremiumCalculationRequestDTO request) {
        PremiumCalculationStrategy strategy = strategies.get(request.getProductCode());

        if (strategy == null) {
            throw new IllegalArgumentException("Invalid product code: " + request.getProductCode());
        }

        // Delegates the complex calculation logic to the selected strategy
        return strategy.calculate(request);
    }
}
