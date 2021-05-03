package com.socialbank.tech.test.controller.response;

import com.socialbank.tech.test.model.AccountStatus;

import java.time.LocalDateTime;

public class AccountResponse {

    private double amount;
    private String identifier;
    private String name;
    private String description;
    private AccountStatus status;
    private LocalDateTime createdDate;

    public double getAmount() {
        return amount;
    }

    public AccountResponse setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public AccountResponse setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccountResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public AccountResponse setStatus(AccountStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public AccountResponse setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
