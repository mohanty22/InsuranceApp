package com.MyProject.InsuranceApp.repo;

import com.MyProject.InsuranceApp.entity.CkycTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CkycTransactionLogRepo extends JpaRepository<CkycTransactionLog, Long> {
}