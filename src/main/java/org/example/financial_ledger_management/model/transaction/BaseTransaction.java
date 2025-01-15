package org.example.financial_ledger_management.model.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.financial_ledger_management.model.Account;
import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.enums.TransactionType;

import java.math.BigDecimal;
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
    @GeneratedValue
    private UUID id;

    /**
     * Счет, к которому относится транзакция
     */
    @ManyToOne(cascade = CascadeType.ALL)
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
