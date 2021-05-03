package com.socialbank.tech.test.controller.request;

import com.socialbank.tech.test.model.AccountStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRequest {

    @NotNull
    @NotEmpty
    private String identifier;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    private AccountStatus status;

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AccountStatus getStatus() {
        return status;
    }
}
