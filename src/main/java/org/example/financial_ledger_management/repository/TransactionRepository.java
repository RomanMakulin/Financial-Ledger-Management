package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
