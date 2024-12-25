package org.example.financial_ledger_management.model.transaction;

import lombok.Getter;

/**
 * Интервалы транзакций
 */
@Getter
public enum TransactionInterval {

    EVERY_DAY("Каждый день"),
    EVERY_WEEK("Каждую неделю"),
    EVERY_MONTH("Каждый месяц");

    private final String description;

    TransactionInterval(String description) {
        this.description = description;
    }

}
