package com.gophergroceries.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gophergroceries.model.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	List<CategoryEntity> findAll();
}
