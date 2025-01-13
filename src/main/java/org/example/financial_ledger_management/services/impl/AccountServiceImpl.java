package org.example.financial_ledger_management.services.impl;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.User;
import org.example.financial_ledger_management.model.dto.AddNewAccount;
import org.example.financial_ledger_management.model.dto.UpdateAccountDto;
import org.example.financial_ledger_management.repository.AccountRepository;
import org.example.financial_ledger_management.services.AccountService;
import org.example.financial_ledger_management.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     *
     * @return список счетов
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Создает новый счет
     *
     * @param accountInfo счет для добавления (информация заданная пользователем)
     * @return созданный счет
     */
    @Override
    @Transactional
    public Account addAccount(AddNewAccount accountInfo) {
        return createAccount(accountInfo);
    }

    /**
     * Обновляет счет по его идентификатору и авторизованному пользователю
     * @param accountInfo данные для обновления счета
     * @return обновленный счет
     */
    @Override
    @Transactional
    public Account updateAccount(UpdateAccountDto accountInfo) {
        User curentUser = authService.getAuthenticatedUser();
        Account accountForUpdate = getAccountByIdAndUser(accountInfo.getAccountId(), curentUser);

        accountForUpdate.setName(accountInfo.getName());
        accountRepository.save(accountForUpdate);
        log.info("Account updated. Name: {}, Account id: {}", accountForUpdate.getName(), accountForUpdate.getId());

        return accountForUpdate;
    }

    /**
     * Удаляет счет по его идентификатору
     *
     * @param id идентификатор счета
     * @return сообщение об успешном удалении
     */
    @Override
    public String deleteAccount(UUID id) {
        if (accountRepository.existsAccountById(id)) {
            accountRepository.deleteById(id);
            log.info("Account with id: {} successfully deleted", id);
            return "Account with id: " + id + " successfully deleted";
        }
        log.info("Account with id: {} not found for deletion", id);
        return "Account with id: " + id + " not found for deletion";
    }

    /**
     * Создает новый счет
     *
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

    /**
     * Проверяет корректность введенных данных для обновления счета
     * @param accountInfo данные для обновления счета
     */
    @Deprecated // теперь используем валидацию в контроллере на принимаемом body
    private void checkUpdateAccountDto(UpdateAccountDto accountInfo) {
        if (accountInfo.getAccountId() == null || accountInfo.getName() == null || accountInfo.getName().isEmpty()) {
            log.error("Invalid account information for update!");
            throw new IllegalArgumentException("Invalid account information for update!");
        }
    }

    /**
     * Получает счет по его идентификатору юзера и идентификатору счета
     * @param accountId идентификатор счета
     * @param user юзер
     * @return счет
     */
    private Account getAccountByIdAndUser(UUID accountId, User user) {
        return accountRepository.findAccountByUserAndId(user, accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found!"));
    }

}
