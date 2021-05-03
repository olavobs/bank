package com.socialbank.tech.test.controller.request;

import javax.validation.constraints.NotNull;

public class TransferRequest {

    @NotNull
    private Double transactionAmount;

    @NotNull
    private String targetIdentifier;

    private String description;

    public String getDescription() {
        return description;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public String getTargetIdentifier() {
        return targetIdentifier;
    }
}
