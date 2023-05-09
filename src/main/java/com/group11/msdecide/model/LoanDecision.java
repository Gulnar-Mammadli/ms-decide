package com.group11.msdecide.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdecide.model.enums.Status;
import jakarta.persistence.*;
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
@Entity
@Table(name = "decisions")
public class LoanDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @JsonProperty("customer_id")
    @Column(nullable = false,name = "customer_id")
    private Long customerId;

    @JsonProperty("loan_id")
    @Column(nullable = false,name = "loan_id")
    private Long loanId;

    @JsonProperty("decided_amount")
    @Column(nullable = false, name = "decided_amount")
    private BigDecimal decidedAmount;

    @JsonProperty("decision_date")
    @Column(nullable = false, name = "decision_date")
    private LocalDate decisionDate;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;

//    TODO decisionu update etmek olur?
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
