package org.example.financial_ledger_management.model.enums;

/**
 * Валюта счета
 */
public enum Currency {

    USD("Доллар США"),
    EUR("Евро"),
    RUB("Российский рубль"),
    GBP("Британский фунт стерлингов"),
    JPY("Японская иена"),
    CHF("Швейцарский франк"),
    AUD("Австралийский доллар"),
    CAD("Канадский доллар"),
    CNY("Китайский юань"),
    INR("Индийская рупия");

    private final String description;

    Currency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}