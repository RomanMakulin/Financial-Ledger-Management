package org.example.financial_ledger_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String currency;

    /**
     * Список транзакций, связанных со счетом.
     */
    @OneToMany(mappedBy = "account")
    private List<BaseTransaction> transactions = new ArrayList<>();
}
