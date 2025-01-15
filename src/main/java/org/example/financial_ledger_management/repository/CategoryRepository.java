package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий категорий
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    /**
     * Поиск категории по имени
     *
     * @param name имя категории
     * @return категория
     */
    Optional<Category> findByName(String name);

}
