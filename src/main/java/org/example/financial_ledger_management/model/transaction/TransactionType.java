package org.example.financial_ledger_management.model.transaction;

import lombok.Getter;

/**
 * Тип транзакции
 */
@Getter
public enum TransactionType {
    INCREASE("Доход"),
    DECREASE("Расход");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

}
