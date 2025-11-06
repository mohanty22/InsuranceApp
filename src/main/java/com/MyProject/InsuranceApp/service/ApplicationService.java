package com.MyProject.InsuranceApp.service;

import com.MyProject.InsuranceApp.dto.AppRequestDTO;
import com.MyProject.InsuranceApp.dto.SearchResponseDTO;
import com.MyProject.InsuranceApp.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Optional<Application> getApplicationByAppnum(String appnum);
    Application createApplication(AppRequestDTO application);

    List<SearchResponseDTO> searchByTouchpointAndInstype(String touchpoint, String instype);

}