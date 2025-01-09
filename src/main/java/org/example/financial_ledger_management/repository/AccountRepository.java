package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
