package org.example.financial_ledger_management.model;

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
@Table(name = "сategory")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<BaseTransaction> transactions = new ArrayList<>();

}
