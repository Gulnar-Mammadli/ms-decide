package com.group11.msdecide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FinancialInfoDto {

    private Long customerId;
    private BigDecimal annualIncome;

    private BigDecimal monthlyTurnover;
    private BigDecimal currentBalance;
    private BigDecimal annualAverageBalance;

    private BigDecimal currentDueDays;
    private int numberOfDueDays;
}
