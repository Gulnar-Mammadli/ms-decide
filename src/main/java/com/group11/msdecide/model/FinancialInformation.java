package com.group11.msdecide.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "financialInformation")
public class FinancialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    TODO hem de customerId olmali deyilmi?
    private BigDecimal annualIncome;

    private BigDecimal monthlyTurnover;
    private BigDecimal currentBalance;
    private BigDecimal annualAverageBalance;

    private BigDecimal currentDueDays;
    private int numberOfDueDays;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
