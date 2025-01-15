package org.example.financial_ledger_management.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO для создания транзакции
 */
public class CreateTransactionDto {

    @NotNull(message = "Необходимо указать ID счета")
    private UUID accountId;

    @NotNull(message = "Необходимо указать категорию")
    private String category;

    @NotNull(message = "Необходимо указать тип транзакции")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull(message = "Необходимо указать сумму")
    private BigDecimal amount;

    private String description;

    private LocalDateTime date;

    // Getters and setters

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreateTransactionDto{" +
                "accountId=" + accountId +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
