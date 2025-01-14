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
     * Получение всех транзакций
     * @return список транзакций
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getTransaction() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        // TODO
        return null;
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
