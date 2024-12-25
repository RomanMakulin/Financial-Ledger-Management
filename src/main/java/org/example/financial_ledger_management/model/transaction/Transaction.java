package org.example.financial_ledger_management.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Сущность для хранения информации об обычных транзакциях
 */
@EqualsAndHashCode(callSuper = true)
@Entity()
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction extends BaseTransaction{

    /**
     * Дата транзакции.
     */
    @Column(nullable = false)
    private LocalDateTime date;

}
