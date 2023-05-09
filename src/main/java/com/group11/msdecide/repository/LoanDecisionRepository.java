package com.group11.msdecide.repository;

import com.group11.msdecide.model.LoanDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanDecisionRepository extends JpaRepository<LoanDecision,Long> {

    Optional<LoanDecision> findByLoanId(Long longId);
    Optional<LoanDecision> findByLoanIdAndId(Long loanId, Long id);
}
