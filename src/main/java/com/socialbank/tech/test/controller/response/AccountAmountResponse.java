package com.socialbank.tech.test.controller.response;

public class AccountAmountResponse {
    private Double amount;
    private String identifier;

    public Double getAmount() {
        return amount;
    }

    public AccountAmountResponse setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public AccountAmountResponse setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

}
