package com.group11.msdecide.repository;

import com.group11.msdecide.model.FinancialInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialInfoRepository extends JpaRepository<FinancialInformation,Long> {
    Optional<FinancialInformation> findByCustomerId(Long customerId);
}
