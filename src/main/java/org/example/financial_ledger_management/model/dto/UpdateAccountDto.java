package org.example.financial_ledger_management.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO обновляющий данные счета
 */
public class UpdateAccountDto {

    /**
     * Идентификатор счета
     */
    @NotNull(message = "Необходимо указать идентификатор счета")
    private UUID accountId;

    /**
     * Имя счета
     */
    @NotNull(message = "Необходимо указать имя счета")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

}
