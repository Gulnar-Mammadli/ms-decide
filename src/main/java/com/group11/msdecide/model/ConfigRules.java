package com.group11.msdecide.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "configRules")
public class ConfigRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal maxLoanAmount;
    private BigDecimal minLoanAmount;
    private LocalDate maxDueDay;
    private BigDecimal debtToIncomeRatio;
    private BigDecimal movingAvgBalanceLowerLimit;

//    TODO
    private BigDecimal movingAvgBalanceUpperLimit;

}
