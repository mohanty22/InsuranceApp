package com.MyProject.InsuranceApp.repo;

import com.MyProject.InsuranceApp.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Optional<Application> findByAppnum(String appnum);
    List<Application> findByTouchpointAndInstype(String touchpoint, String instype);

}


