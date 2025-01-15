package org.example.financial_ledger_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.financial_ledger_management.model.transaction.BaseTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Категория транзакций
 */
@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Название категории
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Список транзакций для конкретной категории
     */
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<BaseTransaction> transactions = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BaseTransaction> transactions) {
        this.transactions = transactions;
    }
}
