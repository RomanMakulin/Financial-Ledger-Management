package org.example.financial_ledger_management.model.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Сущность для хранения информации о планируемых транзакциях
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity()
@Table(name = "scheduled_transaction")
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTransaction extends BaseTransaction{

    /**
     * Дата транзакции.
     */
    @Column(nullable = false, name = "next_run_date")
    private LocalDateTime nextRunDate ;

    /**
     * Интервал транзакции.
     */
    @Column(nullable = false, name = "interval")
    private TransactionInterval interval;

    public LocalDateTime getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(LocalDateTime nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public TransactionInterval getInterval() {
        return interval;
    }

    public void setInterval(TransactionInterval interval) {
        this.interval = interval;
    }
}
