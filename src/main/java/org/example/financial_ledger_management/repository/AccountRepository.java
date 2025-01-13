package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с счетами
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    /**
     * Найти счет по пользователю и имени
     * @param user юзер
     * @param id уникальный идентификатор счета
     * @return счет
     */
    Optional<Account> findAccountByUserAndId(User user, UUID id);

    /**
     * Проверить существование счета по идентификатору
     * @param id уникальный идентификатор счета
     * @return true если счет существует
     */
    boolean existsAccountById(UUID id);
}
