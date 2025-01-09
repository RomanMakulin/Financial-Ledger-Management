package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.transaction.ScheduledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, UUID> {
}
