package org.example.financial_ledger_management.services;

import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.dto.CreateTransactionDto;

/**
 * Интерфейс сервиса категорий
 */
public interface CategoryService {

    /**
     * Получить категорию по названию
     * @param name название категории
     * @return категория
     */
    Category getCategoryByName(String name);

    /**
     * Создать категорию
     * @param name название категории
     * @return новая категория
     */
    Category getCategoryByNameOrCreateNew(String name);
}
