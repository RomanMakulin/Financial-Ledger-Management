package org.example.financial_ledger_management.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Обновление транзакции
 */
public class UpdateTransactionDto {

    @NotNull(message = "Необходимо указать ID транзакции")
    private UUID id;

    @NotNull(message = "Необходимо указать ID счета")
    private UUID accountId;

    @NotNull(message = "Необходимо указать категорию")
    private String category;

    @NotNull(message = "Необходимо указать тип транзакции")
    private String type;

    private String description;

    // Getters and Setters


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UpdateTransactionDto{" +
                "accountId=" + accountId +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
