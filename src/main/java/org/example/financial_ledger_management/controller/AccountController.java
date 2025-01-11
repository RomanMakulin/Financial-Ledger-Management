package org.example.financial_ledger_management.controller;

import org.example.financial_ledger_management.model.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    /**
     * /accounts: Получить список всех счетов.
     * @return Список счетов.
     */
    @GetMapping
    public String accounts() {
        // TODO
        return "accounts";
    }

    @PostMapping
    public String addAccount(@RequestBody Account account) {
        // TODO
        return "Account added";
    }

    @PutMapping("/{id}")
    public String updateAccount(@PathVariable int id, @RequestBody Account account) {
        // TODO
        return "Account updated";
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable int id) {
        // TODO
        return "Account deleted";
    }

}
