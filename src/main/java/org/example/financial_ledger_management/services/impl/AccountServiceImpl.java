package org.example.financial_ledger_management.services.impl;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.dto.AddNewAccount;
import org.example.financial_ledger_management.model.dto.UpdateAccountDto;
import org.example.financial_ledger_management.repository.AccountRepository;
import org.example.financial_ledger_management.services.AccountService;
import org.example.financial_ledger_management.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы со счетами
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    /**
     * Репозиторий для хранения счетов
     */
    private final AccountRepository accountRepository;

    /**
     * Сервис для работы с авторизацией пользователей
     */
    private final AuthService authService;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AuthService authService) {
        this.accountRepository = accountRepository;
        this.authService = authService;
    }

    /**
     * Возвращает все счета
     * @return список счетов
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Создает новый счет
     * @param accountInfo счет для добавления (информация заданная пользователем)
     * @return созданный счет
     */
    @Override
    public Account addAccount(AddNewAccount accountInfo) {
        return createAccount(accountInfo);
    }

    @Override
    public Account updateAccount(UpdateAccountDto account) {
        return null;
    }

    @Override
    public String deleteAccount(UUID id) {
        return null;
    }

    /**
     * Создает новый счет
     * @param accountInfo информация о новом счете от пользователя
     * @return созданный счет
     */
    private Account createAccount(AddNewAccount accountInfo) {
        Account newAccount = new Account();
        newAccount.setName(accountInfo.getName());
        newAccount.setCurrency(accountInfo.getCurrency());
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setUser(authService.getAuthenticatedUser());
        accountRepository.save(newAccount);
        log.info("New account created. Name: {}", newAccount.getName());
        return newAccount;
    }

}
