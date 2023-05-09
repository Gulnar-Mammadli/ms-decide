package com.group11.msdecide.controller;


import com.group11.msdecide.model.LoanDecision;
import com.group11.msdecide.model.dto.LoanDecisionDto;
import com.group11.msdecide.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decisions")
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping
    LoanDecision makeDecision(@RequestBody LoanDecisionDto dto){
        return decisionService.makeDecision(dto);
    }

    @GetMapping
    LoanDecision getDecision(@PathVariable Long loanId){
        return decisionService.getDecision(loanId);
    }
}
