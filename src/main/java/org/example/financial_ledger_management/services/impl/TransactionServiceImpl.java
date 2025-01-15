package org.example.financial_ledger_management.services.impl;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.model.dto.UpdateTransactionDto;
import org.example.financial_ledger_management.model.transaction.Transaction;
import org.example.financial_ledger_management.repository.TransactionRepository;
import org.example.financial_ledger_management.services.AccountService;
import org.example.financial_ledger_management.services.TransactionService;
import org.example.financial_ledger_management.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для работы с транзакциями
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    /**
     * Репозиторий транзакций
     */
    private final TransactionRepository transactionRepository;

    /**
     * Сервис для работы со счетами
     */
    private final AccountService accountService;

    /**
     * Сервис для работы с авторизацией
     */
    private final AuthService authService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService,
                                  AuthService authService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.authService = authService;
    }

    /**
     * Возвращает все транзакции со всех счетов у текущего пользователя
     *
     * @return транзакции
     */
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAllTransactionsByUserId(authService.getAuthenticatedUser().getId());
        log.info("getAllTransactions called. List size: {}", transactions.size());
        return transactions;
    }

    /**
     * Возвращает все транзакции конкретного счета у текущего пользователя
     *
     * @param accountId - id счета
     * @return транзакции
     */
    @Override
    public List<Transaction> getAllTransactionsByAccountId(UUID accountId) {
        List<Transaction> transactions = transactionRepository.findAllTransactionsByAccountIdAndUserId(accountId, authService.getAuthenticatedUser().getId());
        log.info("getAllTransactionsByAccountId called. List size: {}", transactions.size());
        return transactions;
    }

    /**
     * Создает транзакцию
     *
     * @param createTransactionDto - данные транзакции
     * @return транзакция
     */
    @Override
    @Transactional
    public Transaction createTransaction(CreateTransactionDto createTransactionDto) {

        Account account = accountService.getAccountById(createTransactionDto.getAccountId());

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory(createTransactionDto.getCategory());
        transaction.setType(createTransactionDto.getType());
        transaction.setAmount(createTransactionDto.getAmount());
        transaction.setAccount(account);
        Optional.ofNullable(createTransactionDto.getDescription()).ifPresent(transaction::setDescription);

        log.info("createTransaction called. Saving transaction: {}", transaction);
        return transactionRepository.save(transaction);
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
