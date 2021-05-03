package com.socialbank.tech.test.service;

import com.socialbank.tech.test.controller.AccountHistoryConverter;
import com.socialbank.tech.test.controller.response.AccountTransactionHistoryResponse;
import com.socialbank.tech.test.model.AccountTransactionHistory;
import com.socialbank.tech.test.repository.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {

    private final AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private StatementService(AccountHistoryRepository accountHistoryRepository) {
        this.accountHistoryRepository = accountHistoryRepository;
    }

    public List<AccountTransactionHistoryResponse> getStatement(String identifier) {
        return accountHistoryRepository.findBySourceAccountIdentifier(identifier)
                .stream()
                .sorted(Comparator.comparing(AccountTransactionHistory::getTransactionDate))
                .map(transaction -> AccountHistoryConverter.convertFromEntity(transaction))
                .collect(Collectors.toList());
    }
}
