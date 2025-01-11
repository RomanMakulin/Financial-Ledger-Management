package org.example.financial_ledger_management.model.enums;

import lombok.Getter;

/**
 * Тип транзакции
 */
public enum TransactionType {
    INCREASE("Доход"),
    DECREASE("Расход");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
