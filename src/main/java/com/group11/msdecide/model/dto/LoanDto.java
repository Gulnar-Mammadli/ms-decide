package com.group11.msdecide.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.group11.msdecide.model.enums.LoanType;
import com.group11.msdecide.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @JsonProperty("loan_type")
    private LoanType loanType;

    private BigDecimal amount;

    private String term;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
