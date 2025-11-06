package com.MyProject.InsuranceApp.service;

import com.MyProject.InsuranceApp.dto.CheckCkycRequestDTO;
import com.MyProject.InsuranceApp.dto.CheckCkycResponseDTO;
import com.MyProject.InsuranceApp.dto.CkycDetailsResponseDTO;
import com.MyProject.InsuranceApp.dto.FetchCkycDetailsRequestDTO;

public interface CkycService {
    CheckCkycResponseDTO checkCkyc(CheckCkycRequestDTO dto);
    CkycDetailsResponseDTO fetchCkycDetails(FetchCkycDetailsRequestDTO dto);
}
