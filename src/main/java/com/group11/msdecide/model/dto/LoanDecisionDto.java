package com.group11.msdecide.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanDecisionDto {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("loan_id")
    private Long loanId;

    @JsonProperty("decided_amount")
    private BigDecimal decidedAmount;

}
