package com.MyProject.InsuranceApp.controller;

import com.MyProject.InsuranceApp.dto.*;
import com.MyProject.InsuranceApp.entity.Application;
import com.MyProject.InsuranceApp.response.ApiHeader;
import com.MyProject.InsuranceApp.response.ApiResponse;
import com.MyProject.InsuranceApp.service.ApplicationService;

import com.MyProject.InsuranceApp.service.CkycService;
import com.MyProject.InsuranceApp.wrappers.SearchResponseWrapperDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService appService;
    private final CkycService ckycService;

    public ApplicationController(ApplicationService appService ,CkycService ckycService) {
        this.appService = appService;
        this.ckycService = ckycService;
    }

    @GetMapping("/{appnum}")
    public ResponseEntity<ApiResponse<?>> getByAppnum(@PathVariable String appnum) {
        Optional<Application> appOpt = appService.getApplicationByAppnum(appnum);

        if (appOpt.isPresent()) {
            ApiHeader header = new ApiHeader("SUCCESS", "Application retrieved", "GET_APP_BY_APPNUM");
            ApiResponse<Application> response = new ApiResponse<>(header, appOpt.get());
            return ResponseEntity.ok(response);
        } else {
            ApiHeader header = new ApiHeader("FAILURE", "Application not found", "GET_APP_BY_APPNUM");
            ApiResponse<String> response = new ApiResponse<>(header, null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createApplication(@Valid @RequestBody AppRequestDTO dto) {
        Application savedApp = appService.createApplication(dto); // service handles mapping
        ApiHeader header = new ApiHeader("SUCCESS", "Application created", "CREATE_APPLICATION");
        ApiResponse<Application> response = new ApiResponse<>(header, savedApp);
        return ResponseEntity.status(201).body(response);
    }
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<SearchResponseWrapperDTO>> searchApplications(
            @RequestHeader("actionId") String actionId,
            @RequestBody SearchRequestDTO request
    ) {
        List<SearchResponseDTO> results = appService.searchByTouchpointAndInstype(
                request.getTouchpoint(), request.getInstype());

        if (results.isEmpty()) {
            ApiHeader header = new ApiHeader("INCOMPLETE", "No data found", actionId);
            ApiResponse<SearchResponseWrapperDTO> response = new ApiResponse<>(header, null);
            return ResponseEntity.ok(response);
        }

        SearchResponseWrapperDTO wrapper = new SearchResponseWrapperDTO(results);
        ApiHeader header = new ApiHeader("SUCCESS", "Search completed", actionId);
        ApiResponse<SearchResponseWrapperDTO> response = new ApiResponse<>(header, wrapper);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/check-ckyc")
    public ResponseEntity<ApiResponse<CheckCkycResponseDTO>> checkCkyc(
            @RequestHeader("actionId") String actionId,
            @Valid @RequestBody CheckCkycRequestDTO request) {

        CheckCkycResponseDTO ckycCheckResponse = ckycService.checkCkyc(request); // Call the new service

        ApiHeader header;
        if (ckycCheckResponse.getOtp() != null) {
            header = new ApiHeader("SUCCESS", "CKYC check successful.", actionId);
        } else {
            header = new ApiHeader("FAILURE", ckycCheckResponse.getMessage(), actionId);
        }
        ApiResponse<CheckCkycResponseDTO> response = new ApiResponse<>(header, ckycCheckResponse);

        return ResponseEntity.ok(response);
    }
    // To fetch Ckyc details
    @PostMapping("/fetch-ckyc-details")
    public ResponseEntity<ApiResponse<CkycDetailsResponseDTO>> fetchCkycDetails(
            @RequestHeader("actionId") String actionId,
            @Valid @RequestBody FetchCkycDetailsRequestDTO request) {

        CkycDetailsResponseDTO ckycDetailsResponse = ckycService.fetchCkycDetails(request);

        if (ckycDetailsResponse != null) {
            ApiHeader header = new ApiHeader("SUCCESS", "CKYC details fetched successfully.", actionId);
            ApiResponse<CkycDetailsResponseDTO> response = new ApiResponse<>(header, ckycDetailsResponse);
            return ResponseEntity.ok(response);
        } else {
            ApiHeader header = new ApiHeader("FAILURE", "Session or OTP is invalid.", actionId);
            ApiResponse<CkycDetailsResponseDTO> response = new ApiResponse<>(header, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}


