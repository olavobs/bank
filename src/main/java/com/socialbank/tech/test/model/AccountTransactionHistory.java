package com.socialbank.tech.test.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_history")
public class AccountTransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    private Double transactionAmount;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;

    public Long getId() {
        return id;
    }

    public AccountTransactionHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public AccountTransactionHistory setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
        return this;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public AccountTransactionHistory setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public AccountTransactionHistory setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public AccountTransactionHistory setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccountTransactionHistory setDescription(String description) {
        this.description = description;
        return this;
    }
}
