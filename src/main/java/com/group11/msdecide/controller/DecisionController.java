package com.group11.msdecide.controller;


import com.group11.msdecide.model.FinancialInformation;
import com.group11.msdecide.model.LoanDecision;
import com.group11.msdecide.model.dto.FinancialInfoDto;
import com.group11.msdecide.model.dto.LoanDto;
import com.group11.msdecide.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decisions")
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping
    LoanDecision makeDecision(@RequestBody LoanDto loanDto){
        return decisionService.makeDecision(loanDto);
    }

    @GetMapping("/{loanId}")
    LoanDecision getDecision(@PathVariable Long loanId){
        return decisionService.getDecision(loanId);
    }

    @PostMapping("/finance")
    FinancialInformation addFinancialInfo(@RequestBody FinancialInfoDto infoDto){
        return decisionService.addFinancialInfo(infoDto);
    }

    @GetMapping("/{customerId}")
    FinancialInformation getFinancialInfo(@PathVariable Long customerId){
        return decisionService.getFinancialInfo(customerId);
    }
}
