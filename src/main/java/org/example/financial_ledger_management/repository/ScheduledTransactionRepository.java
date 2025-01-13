package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.transaction.ScheduledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, UUID> {
}
