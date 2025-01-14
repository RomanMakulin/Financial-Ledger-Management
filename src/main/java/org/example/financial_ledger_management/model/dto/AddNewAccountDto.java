package org.example.financial_ledger_management.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.financial_ledger_management.model.enums.Currency;

public class AddNewAccountDto {

    /**
     * Имя счета.
     */
    @NotNull(message = "Имя счета не может быть пустым")
    private String name;

    /**
     * Валюта счета.
     */
    @NotNull(message = "Валюта счета не может быть пустой")
    private Currency currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
