package org.example.financial_ledger_management.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.repository.CategoryRepository;
import org.example.financial_ledger_management.services.CategoryService;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с категориями
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * Репозиторий категорий
     */
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Получение категории по названию
     * @param name название категории
     * @return категория
     */
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + name));
    }

    /**
     * Получение категории по названию или создание новой
     * @param name название категории
     * @return категория
     */
    @Override
    public Category getCategoryByNameOrCreateNew(String name){
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(name);
                    return categoryRepository.save(newCategory);
                });
    }

}
