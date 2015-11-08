package com.gophergroceries.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.entities.CategoryEntity;
import com.gophergroceries.model.repository.CategoryRepository;

@Service("categoryMapping")
public class CategoryMappingService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryEntity> getCategoryList() {
		return categoryRepository.findAll();
	}

}
