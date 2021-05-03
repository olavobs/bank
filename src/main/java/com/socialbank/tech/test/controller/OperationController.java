package com.socialbank.tech.test.controller;

import com.socialbank.tech.test.controller.request.DepositRequest;
import com.socialbank.tech.test.controller.request.TransferRequest;
import com.socialbank.tech.test.controller.response.AccountTransactionHistoryResponse;
import com.socialbank.tech.test.service.OperationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    private OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @ApiOperation(value = "Retorna o registo do depósito realizado")
    @PostMapping("/deposit/{identifier}")
    public AccountTransactionHistoryResponse deposit(@PathVariable(value = "identifier") String identifier, @RequestBody @Valid DepositRequest depositRequest) {
        return operationService.deposit(identifier, depositRequest);
    }

    @ApiOperation(value = "Retorna o registo da transferência realizada")
    @PostMapping("/transfer/{identifier}")
    public AccountTransactionHistoryResponse deposit(@PathVariable(value = "identifier") String identifier, @RequestBody @Valid TransferRequest transferRequest) {
        return operationService.transfer(identifier, transferRequest);
    }

}
