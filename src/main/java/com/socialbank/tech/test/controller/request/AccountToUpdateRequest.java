package com.socialbank.tech.test.controller.request;

import com.socialbank.tech.test.model.AccountStatus;

public class AccountToUpdateRequest {

    private String name;

    private String description;

    private AccountStatus status;

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
