package com.group11.msdecide.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdecide.model.enums.DecisionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DecisionDto {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("loan_id")
    private Long loanId;

    @JsonProperty("decided_amount")
    private BigDecimal decidedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DecisionStatus decisionStatus;


    @JsonProperty("decision_date")
    private LocalDate decisionDate;



}
