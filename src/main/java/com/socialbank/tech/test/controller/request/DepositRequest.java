package com.socialbank.tech.test.controller.request;

import javax.validation.constraints.NotNull;

public class DepositRequest {

    @NotNull
    private Double transactionAmount;

    public Double getTransactionAmount() {
        return transactionAmount;
    }

}
