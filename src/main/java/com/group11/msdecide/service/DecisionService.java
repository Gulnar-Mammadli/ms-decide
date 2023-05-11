package com.group11.msdecide.service;

import com.group11.msdecide.model.LoanDecision;
import com.group11.msdecide.model.dto.LoanDto;
import com.group11.msdecide.model.enums.DecisionStatus;
import com.group11.msdecide.model.enums.Status;
import com.group11.msdecide.repository.LoanDecisionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionService {

    private final LoanDecisionRepository decisionRepository;

    private final KafkaTemplate<String, LoanDto> kafkaTemplate;
    @KafkaListener(topics = "loanCreatedTopic", groupId = "loanEventGroup")
    public LoanDecision makeDecision(LoanDto loanDto) {

        log.info("Log message - received from loan topic: {} ", loanDto.toString());

        LoanDecision decision = LoanDecision.builder()
                .loanId(loanDto.getId())
                .customerId(loanDto.getCustomerId())
                .decisionDate(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .decisionStatus(DecisionStatus.APPROVED)
                .decidedAmount(BigDecimal.valueOf(1000))
                .build();

        int i = decision.getDecidedAmount().compareTo(loanDto.getAmount());
        if (decision.getDecisionStatus() == DecisionStatus.APPROVED && i==0) {
            loanDto.setStatus(Status.ACCEPTED);
            loanDto.setUpdatedAt(LocalDateTime.now());
            log.info("Log message - a loan status for loan id: {} has been accepted successfully", loanDto.toString());
            kafkaTemplate.send("decisionTopic", loanDto);

        } else if (decision.getDecisionStatus() == DecisionStatus.APPROVED && i>0) {
            loanDto.setStatus(Status.OFFERED);
            loanDto.setUpdatedAt(LocalDateTime.now());
            log.info("Log message - a loan status for loan id: {} has been offered successfully", loanDto.toString());

            kafkaTemplate.send("decisionTopic", loanDto);


        } else if (decision.getDecisionStatus() == DecisionStatus.REJECTED) {
            loanDto.setStatus(Status.REFUSED);
            loanDto.setUpdatedAt(LocalDateTime.now());
            log.info("Log message - a loan status for loan id: {} has been refused", loanDto.toString());

            kafkaTemplate.send("decisionTopic", loanDto);

        }
        return decisionRepository.save(decision);

    }

    public LoanDecision getDecision(Long loanId) {
        Optional<LoanDecision> result = decisionRepository.findByLoanId(loanId);
        return result.orElse(null);
    }
}
