package org.example.financial_ledger_management.services;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.User;
import org.example.financial_ledger_management.model.dto.AddNewAccountDto;
import org.example.financial_ledger_management.model.dto.UpdateAccountDto;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для работы со счетами.
 */
public interface AccountService {

    /**
     * Возвращает список всех счетов.
     *
     * @return список счетов
     */
    List<Account> getAllAccounts();

    /**
     * Добавляет новый счет.
     *
     * @param account счет для добавления
     * @return сообщение о результате добавления
     */
    Account addAccount(AddNewAccountDto account);

    /**
     * Обновляет существующий счет.
     *
     * @param account данные для обновления счета
     * @return сообщение о результате обновления
     */
    Account updateAccount(UpdateAccountDto account);

    /**
     * Удаляет счет по идентификатору.
     *
     * @param id идентификатор счета для удаления
     * @return сообщение о результате удаления
     */
    String deleteAccount(UUID id);

    /**
     * Возвращает счет по идентификатору.
     *
     * @param accountId идентификатор счета
     * @return счет
     */
    Account getAccountById(UUID accountId);

}

