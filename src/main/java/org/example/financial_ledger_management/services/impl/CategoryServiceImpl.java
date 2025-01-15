package org.example.financial_ledger_management.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.financial_ledger_management.model.Category;
import org.example.financial_ledger_management.model.dto.CreateTransactionDto;
import org.example.financial_ledger_management.repository.CategoryRepository;
import org.example.financial_ledger_management.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с категориями
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
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
    @Transactional
    @Override
    public Category getCategoryByNameOrCreateNew(String name){
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    log.info("Category '{}' not found. Creating a new one.", name);
                    Category newCategory = new Category();
                    newCategory.setName(name);
                    return categoryRepository.save(newCategory);
                });
    }

}
