package com.socialbank.tech.test.controller;

import com.socialbank.tech.test.controller.request.AccountRequest;
import com.socialbank.tech.test.controller.request.AccountToUpdateRequest;
import com.socialbank.tech.test.controller.response.AccountAmountResponse;
import com.socialbank.tech.test.controller.response.AccountResponse;
import com.socialbank.tech.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @GetMapping("/{identifier}")
    public AccountResponse getAccount(@PathVariable(value = "identifier") String identifier) {
        return accountService.find(identifier);
    }

    @DeleteMapping("/{identifier}")
    public void cancelAccount(@PathVariable(value = "identifier") String identifier) {
        accountService.cancel(identifier);
    }

    @PatchMapping("/{identifier}")
    public AccountResponse updateAccount(@PathVariable(value = "identifier") String identifier, @RequestBody AccountToUpdateRequest accountToUpdateRequest) {
        return accountService.update(identifier, accountToUpdateRequest);
    }

    @GetMapping("/{identifier}/amount")
    public AccountAmountResponse getAmount(@PathVariable(value = "identifier") String identifier) {
        return accountService.getAmount(identifier);
    }
}
