package com.group11.msdecide.service;

import com.group11.msdecide.model.LoanDecision;
import com.group11.msdecide.model.dto.LoanDecisionDto;
import com.group11.msdecide.model.enums.Status;
import com.group11.msdecide.repository.LoanDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DecisionService {

    private final LoanDecisionRepository decisionRepository;
    public LoanDecision makeDecision(LoanDecisionDto dto) {

        LoanDecision decision = LoanDecision.builder()
                .id(1L)
                .loanId(dto.getLoanId())
                .customerId(dto.getCustomerId())
                .decisionDate(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .status(Status.APPROVED)
                .build();

        return decision;
    }

    public LoanDecision getDecision(Long loanId) {
        Optional<LoanDecision> result = decisionRepository.findByLoanId(loanId);
        return result.orElse(null);
    }
}
