package com.gophergroceries.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gophergroceries.model.entities.ProductEntity;

public interface ProductsRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
	List<ProductEntity> findByPopularIgnoreCase(String popular);
	List<ProductEntity> findAllByOrderByNameAsc();
	List<ProductEntity> findAllByOrderByPriceAsc();
	List<ProductEntity> findByCategoryOrderByNameAsc(String category);
}
