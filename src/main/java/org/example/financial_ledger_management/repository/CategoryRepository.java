package org.example.financial_ledger_management.repository;

import org.example.financial_ledger_management.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
