package com.socialbank.tech.test.controller.response;

import com.socialbank.tech.test.model.TransactionType;

import java.time.LocalDateTime;

public class AccountTransactionHistoryResponse {

    private String sourceAccount;

    private String targetAccount;

    private Double transactionAmount;

    private LocalDateTime date;

    private TransactionType transactionType;

    public String getSourceAccount() {
        return sourceAccount;
    }

    public AccountTransactionHistoryResponse setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
        return this;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public AccountTransactionHistoryResponse setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
        return this;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public AccountTransactionHistoryResponse setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public AccountTransactionHistoryResponse setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public AccountTransactionHistoryResponse setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }
}
