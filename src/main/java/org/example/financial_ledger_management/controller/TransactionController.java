package org.example.financial_ledger_management.controller;

import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.model.dto.UpdateTransactionDto;
import org.example.financial_ledger_management.model.transaction.Transaction;
import org.example.financial_ledger_management.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с транзакциями
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    /**
     * Интерфейс для работы с транзакциями
     */
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Получение всех транзакций со всех счетов у авторизованного пользователя
     *
     * @return список транзакций
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    /**
     * Получение транзакций по id счета у авторизованного пользователя
     *
     * @param accountId идентификатор счета
     * @return транзакции
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountId(@PathVariable("accountId") UUID accountId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByAccountId(accountId));
    }

    /**
     * Создание транзакции
     *
     * @param createTransactionDto полученный объект транзакции
     * @return созданная транзакция
     */
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        return ResponseEntity.ok(transactionService.createTransaction(createTransactionDto));
    }

    @PutMapping()
    public ResponseEntity<Transaction> updateTransaction(@RequestBody UpdateTransactionDto updateTransactionDto) {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable UUID id) {
        // TODO
        return null;
    }

}
