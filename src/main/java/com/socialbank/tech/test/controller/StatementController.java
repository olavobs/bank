package com.socialbank.tech.test.controller;

import com.socialbank.tech.test.controller.response.AccountTransactionHistoryResponse;
import com.socialbank.tech.test.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statement")
public class StatementController {

    private final StatementService statementService;

    @Autowired
    private StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/{identifier}")
    public List<AccountTransactionHistoryResponse> getStatement(@PathVariable(value = "identifier") String identifier) {
        return statementService.getStatement(identifier);
    }


}
