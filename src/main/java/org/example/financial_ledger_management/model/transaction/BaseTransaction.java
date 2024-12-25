package org.example.financial_ledger_management.model.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.Category;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Абстрактный класс транзакции с базовыми полями
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    /**
     * Счет, к которому относится транзакция
     */
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    /**
     * Категория, к которой относится транзакция
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Тип транзакции: доход/расход.
     */
    @Column(nullable = false)
    private TransactionType type;

    /**
     * Сумма транзакции.
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Описание транзакции.
     */
    @Column()
    private String description;
}
