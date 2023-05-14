package com.group11.msdecide.service;

import com.group11.msdecide.model.FinancialInformation;
import com.group11.msdecide.model.LoanDecision;
import com.group11.msdecide.model.dto.DecisionDto;
import com.group11.msdecide.model.dto.FinancialInfoDto;
import com.group11.msdecide.model.dto.LoanDto;
import com.group11.msdecide.model.enums.DecisionStatus;
import com.group11.msdecide.model.enums.Status;
import com.group11.msdecide.repository.FinancialInfoRepository;
import com.group11.msdecide.repository.LoanDecisionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final FinancialInfoRepository financialInfoRepository;
    private final KafkaTemplate<String, DecisionDto> kafkaTemplate;


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

        DecisionDto decisionDto = DecisionDto.builder()
                .loanId(decision.getLoanId())
                .customerId(decision.getCustomerId())
                .decisionDate(decision.getDecisionDate())
                .decisionStatus(decision.getDecisionStatus())
                .decidedAmount(decision.getDecidedAmount())
                .build();

        kafkaTemplate.send("decisionTopic", decisionDto);
        log.info("Log message - a loan status for decision id: {} has been sent successfully", decisionDto.toString());
        return decisionRepository.save(decision);
    }


    public LoanDecision getDecision(Long loanId) {
        Optional<LoanDecision> result = decisionRepository.findByLoanId(loanId);
        return result.orElse(null);
    }

    public FinancialInformation addFinancialInfo(FinancialInfoDto infoDto) {
        Optional<FinancialInformation> info = financialInfoRepository.findByCustomerId(infoDto.getCustomerId());
        if (info.isEmpty()) {
            FinancialInformation information = FinancialInformation.builder()
                    .customerId(infoDto.getCustomerId())
                    .monthlyTurnover(infoDto.getMonthlyTurnover())
                    .annualIncome(infoDto.getAnnualIncome())
                    .annualAverageBalance(infoDto.getAnnualAverageBalance())
                    .currentBalance(infoDto.getCurrentBalance())
                    .currentDueDays(infoDto.getCurrentDueDays())
                    .numberOfDueDays(infoDto.getNumberOfDueDays())
                    .createdAt(LocalDateTime.now())
                    .build();
            return financialInfoRepository.save(information);
        }
        return null;
    }


    public FinancialInformation getFinancialInfo(Long customerId) {
        Optional<FinancialInformation> info = financialInfoRepository.findByCustomerId(customerId);
        return info.orElse(null);
    }
}
