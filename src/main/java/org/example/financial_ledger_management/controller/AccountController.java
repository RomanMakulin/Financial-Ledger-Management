package org.example.financial_ledger_management.controller;

import jakarta.validation.Valid;
import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.dto.AddNewAccountDto;
import org.example.financial_ledger_management.model.dto.UpdateAccountDto;
import org.example.financial_ledger_management.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с счетами
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Получить список всех счетов.
     * @return Список счетов.
     */
    @GetMapping
    public ResponseEntity<List<Account>> accounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    /**
     * Добавить счет.
     * @param account Счет.
     * @return информация о результате запроса
     */
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody @Valid AddNewAccountDto account) {
        return ResponseEntity.ok(accountService.addAccount(account));
    }

    /**
     * Обновить счет.
     * @param account счет для обновления.
     * @return информация о результате запроса
     */
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody @Valid UpdateAccountDto account) {
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    /**
     * Удалить счет.
     * @param id Идентификатор счета.
     * @return информация о результате запроса
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable @Valid UUID id) {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

}
