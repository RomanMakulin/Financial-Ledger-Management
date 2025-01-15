package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий транзакций
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Получение всех транзакций пользователя
     * @param userId id пользователя
     * @return список транзакций
     */
    @Query("SELECT t FROM Transaction t WHERE t.account.user.id = :userId")
    List<Transaction> findAllTransactionsByUserId(@Param("userId") UUID userId);

    /**
     * Получение всех транзакций пользователя по id счета
     * @param accountId id счета
     * @return список транзакций
     */
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.account.user.id = :userId")
    List<Transaction> findAllTransactionsByAccountIdAndUserId(@Param("accountId") UUID accountId, @Param("userId") UUID userId);

}
