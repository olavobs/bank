package com.socialbank.tech.test.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    private static final String DEPOSITO = "Depósito Realizado";
    private static final String TRANSFERENCIA = "Transferência realizada para a conta: ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private Double amount;

    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceAccount")
    private Set<AccountTransactionHistory> accountTransactionHistoryList;

    public String getIdentifier() {
        return identifier;
    }

    public Account setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Account setDescription(String description) {
        this.description = description;
        return this;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Account setStatus(AccountStatus status) {
        this.status = status;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Account setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Account changeAmount(Double amount, TransactionType transactionType, String targetIdentifier) {
        this.amount = Double.sum(this.amount, amount);
        addHistory(amount, transactionType, targetIdentifier);
        return this;
    }

    private void addHistory(Double amount, TransactionType transactionType, String targetIdentifier) {
        if (Objects.isNull(accountTransactionHistoryList)) {
            accountTransactionHistoryList = new HashSet<>();
        }
        accountTransactionHistoryList.add(new AccountTransactionHistory()
                .setTransactionAmount(amount)
                .setTransactionDate(LocalDateTime.now())
                .setTransactionType(transactionType)
                .setSourceAccount(this)
                .setDescription(transactionType.equals(TransactionType.DEPOSIT) ? DEPOSITO : TRANSFERENCIA + targetIdentifier)
        );
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Account setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<AccountTransactionHistory> getAccountTransactionHistoryList() {
        return accountTransactionHistoryList;
    }

    public Account setAccountTransactionHistoryList(Set<AccountTransactionHistory> accountTransactionHistoryList) {
        this.accountTransactionHistoryList = accountTransactionHistoryList;
        return this;
    }
}
