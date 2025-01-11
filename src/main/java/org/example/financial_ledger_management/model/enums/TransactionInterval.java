package org.example.financial_ledger_management.model.enums;

import lombok.Getter;

/**
 * Интервалы транзакций
 */
public enum TransactionInterval {

    EVERY_DAY("Каждый день"),
    EVERY_WEEK("Каждую неделю"),
    EVERY_MONTH("Каждый месяц");

    private final String description;

    TransactionInterval(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
