package com.MyProject.InsuranceApp.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ProductCatalog {

    // Centralized List of available Occupations
    public static final List<String> VALID_OCCUPATIONS = List.of(
            "salaried",
            "self employed",
            "housewife",
            "student",
            "unemployed",
            "retired"
    );

    // Centralized Product Definition
    private final List<ProductMetadata> productList = List.of(
            new ProductMetadata(
                    "TERM_LIFE",
                    "MaxProtect Term Plan",
                    "TERM",
                    500000,
                    50000000,
                    List.of(
                            new RiderMetadata("ACC_DEATH", "Accidental Death Benefit", 1000),
                            new RiderMetadata("CRIT_ILL", "Critical Illness Rider", 3000)
                    )
            ),
            new ProductMetadata(
                    "ENDOWMENT_SAFE",
                    "Safe Future Endowment",
                    "ENDOWMENT",
                    100000,
                    10000000,
                    List.of(
                            new RiderMetadata("WAIVER_PREM", "Waiver of Premium", 500)
                    )
            ),
            new ProductMetadata(
                    "ULIP_GROWTH",
                    "Wealth Growth ULIP",
                    "ULIP",
                    200000,
                    75000000,
                    List.of()
            )
    );

    private final Map<String, ProductMetadata> productMap = productList.stream()
            .collect(Collectors.toMap(ProductMetadata::getCode, Function.identity()));

    public List<ProductMetadata> getAllProducts() {
        return productList;
    }

    public ProductMetadata getProductByCode(String code) {
        return productMap.get(code);
    }

    public List<String> getValidOccupations() {
        return VALID_OCCUPATIONS;
    }
}