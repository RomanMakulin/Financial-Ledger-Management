package org.example.financial_ledger_management.services.impl;

import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.model.dto.UpdateTransactionDto;
import org.example.financial_ledger_management.model.transaction.Transaction;
import org.example.financial_ledger_management.repository.TransactionRepository;
import org.example.financial_ledger_management.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с транзакциями
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    /**
     * Репозиторий транзакций
     */
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Возвращает все транзакции
     * @return транзакции
     */
    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createTransaction(CreateTransactionDto createTransactionDto) {
        return null;
    }

    @Override
    public Transaction updateTransaction(UpdateTransactionDto updateTransactionDto) {
        return null;
    }

    @Override
    public Transaction deleteTransaction(UUID id) {
        return null;
    }
}
