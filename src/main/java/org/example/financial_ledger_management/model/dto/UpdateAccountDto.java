package org.example.financial_ledger_management.model.dto;

import java.util.UUID;

/**
 * DTO обновляющий данные счета
 */
public class UpdateAccountDto {

    /**
     * Идентификатор счета
     */
    private UUID accountId;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    /**
     * Имя счета
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
