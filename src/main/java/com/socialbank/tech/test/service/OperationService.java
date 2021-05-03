package com.socialbank.tech.test.service;

import com.socialbank.tech.test.controller.AccountHistoryConverter;
import com.socialbank.tech.test.controller.request.DepositRequest;
import com.socialbank.tech.test.controller.request.TransferRequest;
import com.socialbank.tech.test.controller.response.AccountTransactionHistoryResponse;
import com.socialbank.tech.test.exception.NotEnoughAmountInAccountException;
import com.socialbank.tech.test.model.Account;
import com.socialbank.tech.test.model.AccountTransactionHistory;
import com.socialbank.tech.test.repository.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
public class OperationService {

    private final AccountService accountService;
    private final AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private OperationService(AccountService accountService, AccountHistoryRepository accountHistoryRepository) {
        this.accountService = accountService;
        this.accountHistoryRepository = accountHistoryRepository;
    }

    public AccountTransactionHistoryResponse deposit(String identifier, DepositRequest depositRequest) {
        var account = accountService.updateAmount(identifier, depositRequest.getTransactionAmount());
        return AccountHistoryConverter.convertFromEntity(account.getAccountTransactionHistoryList()
                .stream()
                .sorted(Comparator.comparing(AccountTransactionHistory::getId).reversed())
                .findFirst()
                .get());
    }

    public AccountTransactionHistoryResponse transfer(String identifier, TransferRequest transferRequest) {
        var account = transferAmount(identifier, transferRequest);
        return AccountHistoryConverter.convertFromEntity(account.getAccountTransactionHistoryList()
                .stream()
                .sorted(Comparator.comparing(AccountTransactionHistory::getId).reversed())
                .findFirst()
                .get());
    }

    @Transactional
    private Account transferAmount(String identifier, TransferRequest transferRequest) {
        var accountSource = accountService.findActiveAccount(identifier);
        validateAmount(accountSource, transferRequest.getTransactionAmount());

        var accountTarget = accountService.findActiveAccount(transferRequest.getTargetIdentifier());
        accountService.updateAmount(accountSource.getIdentifier(), transferRequest.getTransactionAmount(), transferRequest.getTargetIdentifier());
        return accountService.updateAmount(accountTarget.getIdentifier(), -transferRequest.getTransactionAmount(), transferRequest.getTargetIdentifier());
    }

    private void validateAmount(Account accountSource, Double amount) {
        if (Double.sum(accountSource.getAmount(), -amount) < 0) {
            throw new NotEnoughAmountInAccountException();
        }
    }
}
