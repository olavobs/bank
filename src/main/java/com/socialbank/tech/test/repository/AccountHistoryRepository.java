package com.socialbank.tech.test.repository;

import com.socialbank.tech.test.model.AccountTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHistoryRepository extends JpaRepository<AccountTransactionHistory, Long> {
    List<AccountTransactionHistory> findBySourceAccountIdentifier(String identifier);
}
