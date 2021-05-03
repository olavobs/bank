package com.socialbank.tech.test.service;

import com.socialbank.tech.test.controller.request.AccountRequest;
import com.socialbank.tech.test.controller.request.AccountToUpdateRequest;
import com.socialbank.tech.test.controller.response.AccountAmountResponse;
import com.socialbank.tech.test.controller.response.AccountResponse;
import com.socialbank.tech.test.exception.AccountNotActiveException;
import com.socialbank.tech.test.exception.AccountNotFoundException;
import com.socialbank.tech.test.model.Account;
import com.socialbank.tech.test.model.AccountStatus;
import com.socialbank.tech.test.model.TransactionType;
import com.socialbank.tech.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse create(AccountRequest accountRequest) {
        var account = convertFromRequestToEntity(accountRequest);
        account = accountRepository.save(account);
        return convertFromEntityToResponse(account);
    }

    public AccountResponse cancel(String identifier) {
        var account = findAccount(identifier);
        account = accountRepository.save(account.setStatus(AccountStatus.INACTIVE));
        return convertFromEntityToResponse(account);
    }

    public AccountResponse find(String identifier) {
        var account = findAccount(identifier);
        return convertFromEntityToResponse(account);
    }

    public AccountResponse update(String identifier, AccountToUpdateRequest accountToUpdateRequest) {
        var account = findAccount(identifier);
        account = updateAccount(account, accountToUpdateRequest);
        account = accountRepository.save(account);
        return convertFromEntityToResponse(account);
    }

    public AccountAmountResponse getAmount(String identifier) {
        var account = findAccount(identifier);
        return new AccountAmountResponse().setAmount(account.getAmount()).setIdentifier(account.getIdentifier());
    }

    public Account updateAmount(String identifier, Double amount, String targetIdentifier) {
        var account = findActiveAccount(identifier);
        return accountRepository.save(account.changeAmount(amount, TransactionType.TRANSFER, targetIdentifier));
    }

    public Account updateAmount(String identifier, Double amount) {
        var account = findActiveAccount(identifier);
        return accountRepository.save(account.changeAmount(amount, TransactionType.DEPOSIT, null));
    }

    public Account findActiveAccount(String identifier) {
        var account = findAccount(identifier);
        if (account.getStatus() == AccountStatus.INACTIVE) {
            throw new AccountNotActiveException();
        }
        return account;
    }

    private Account updateAccount(Account account, AccountToUpdateRequest accountToUpdateRequest) {
        return account
                .setStatus(nonNull(accountToUpdateRequest.getStatus()) ? accountToUpdateRequest.getStatus() : account.getStatus())
                .setName(nonNull(accountToUpdateRequest.getName()) ? accountToUpdateRequest.getName() : account.getName())
                .setDescription(nonNull(accountToUpdateRequest.getDescription()) ? accountToUpdateRequest.getDescription() : account.getDescription());
    }

    private Account findAccount(String identifier) {
        return accountRepository.findByIdentifier(identifier).orElseThrow(() -> new AccountNotFoundException());
    }

    private Account convertFromRequestToEntity(AccountRequest accountRequest) {
        return new Account()
                .setAmount((double) 0)
                .setDescription(accountRequest.getDescription())
                .setIdentifier(accountRequest.getIdentifier())
                .setName(accountRequest.getName())
                .setCreatedDate(LocalDateTime.now())
                .setStatus(Optional.ofNullable(accountRequest.getStatus()).orElse(AccountStatus.ACTIVE));
    }

    private AccountResponse convertFromEntityToResponse(Account account) {
        return new AccountResponse()
                .setAmount(account.getAmount())
                .setDescription(account.getDescription())
                .setIdentifier(account.getIdentifier())
                .setName(account.getName())
                .setStatus(account.getStatus())
                .setCreatedDate(account.getCreatedDate());
    }

}
