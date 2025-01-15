package org.example.financial_ledger_management.services.impl;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.model.dto.UpdateTransactionDto;
import org.example.financial_ledger_management.model.enums.TransactionType;
import org.example.financial_ledger_management.model.transaction.Transaction;
import org.example.financial_ledger_management.repository.TransactionRepository;
import org.example.financial_ledger_management.services.AccountService;
import org.example.financial_ledger_management.services.CategoryService;
import org.example.financial_ledger_management.services.TransactionService;
import org.example.financial_ledger_management.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private final CategoryService categoryService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService,
                                  AuthService authService,
                                  CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.authService = authService;
        this.categoryService = categoryService;
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
        validateTransaction(createTransactionDto.getAmount(), account.getBalance(), createTransactionDto.getType());

        Category category = categoryService.getCategoryByNameOrCreateNew(createTransactionDto.getCategory());

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory(category);
        transaction.setType(createTransactionDto.getType());
        transaction.setAmount(createTransactionDto.getAmount());
        transaction.setAccount(account);
        Optional.ofNullable(createTransactionDto.getDescription()).ifPresent(transaction::setDescription);

        updateAccountBalance(transaction, account);

        log.info("createTransaction called. Saving transaction: {}", transaction);
        return transactionRepository.save(transaction);
    }

    /**
     * Обновляет баланс счета
     *
     * @param transaction - транзакция
     * @param account - счет
     */
    private void updateAccountBalance(Transaction transaction, Account account) {
        if (transaction.getType() == TransactionType.INCREASE) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        } else if (transaction.getType() == TransactionType.DECREASE) {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        }
    }

    /**
     * Валидация транзакции на отрицательную сумму и сумму превышающую баланс счета при снятии
     *
     * @param transactionAmount - сумма транзакции
     * @param accountBalance - баланс счета
     * @param transactionType - тип транзакции
     */
    private void validateTransaction(BigDecimal transactionAmount, BigDecimal accountBalance, TransactionType transactionType) {
        if (transactionAmount == null || accountBalance == null || transactionType == null) {
            throw new IllegalArgumentException("Transaction amount, account balance, and transaction type cannot be null");
        }

        if (transactionType == TransactionType.DECREASE && transactionAmount.compareTo(accountBalance) > 0) {
            throw new IllegalArgumentException("Transaction amount is greater than account balance");
        }

        if (transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
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
