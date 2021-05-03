package com.socialbank.tech.test.repository;

import com.socialbank.tech.test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIdentifier(String identifier);
}
