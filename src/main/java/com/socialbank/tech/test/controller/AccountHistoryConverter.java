package com.socialbank.tech.test.controller;

import com.socialbank.tech.test.controller.response.AccountTransactionHistoryResponse;
import com.socialbank.tech.test.model.AccountTransactionHistory;

import java.util.Objects;

public class AccountHistoryConverter {

    public static AccountTransactionHistoryResponse convertFromEntity(AccountTransactionHistory transaction) {
        return new AccountTransactionHistoryResponse()
                .setDate(transaction.getTransactionDate())
                .setSourceAccount(Objects.nonNull(transaction.getSourceAccount()) ? transaction.getSourceAccount().getIdentifier() : null)
                .setTransactionAmount(transaction.getTransactionAmount())
                .setTransactionType(transaction.getTransactionType());
    }
}
