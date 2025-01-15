package org.example.financial_ledger_management.services;

import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.model.dto.UpdateTransactionDto;
import org.example.financial_ledger_management.model.transaction.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса транзакций
 */
public interface TransactionService {

    /**
     * Возвращает все транзакции со всех счетов
     *
     * @return транзакции
     */
    List<Transaction> getAllTransactions();

    /**
     * Возвращает все транзакции по конкретному счету
     *
     * @param accountId - id счета
     * @return транзакции
     */
    List<Transaction> getAllTransactionsByAccountId(UUID accountId);

    /**
     * Создает транзакцию
     *
     * @param createTransactionDto - данные транзакции
     * @return транзакция
     */
    Transaction createTransaction(CreateTransactionDto createTransactionDto);

    /**
     * Обновляет транзакцию
     *
     * @param updateTransactionDto - данные транзакции
     * @return транзакция
     */
    Transaction updateTransaction(UpdateTransactionDto updateTransactionDto);

    /**
     * Удаляет транзакцию
     *
     * @param id - идентификатор транзакции
     * @return транзакция
     */
    Transaction deleteTransaction(UUID id);

}
