package org.example.financial_ledger_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.financial_ledger_management.model.enums.Currency;
import org.example.financial_ledger_management.model.transaction.BaseTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность денежного счета
 */
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Имя счета.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Баланс счета.
     */
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    /**
     * Валюта счета.
     */
    @Column(name = "currency", nullable = false)
    private Currency currency;

    /**
     * Список транзакций, связанных со счетом.
     */
    @OneToMany(mappedBy = "account")
    private List<BaseTransaction> transactions = new ArrayList<>();

    /**
     * Пользователь, которому принадлежит счет.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<BaseTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BaseTransaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
