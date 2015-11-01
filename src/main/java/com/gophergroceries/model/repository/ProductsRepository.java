package com.gophergroceries.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gophergroceries.model.entities.Product;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {
	List<Product> findByPopularIgnoreCase(String popular);
	List<Product> findAllByOrderByNameAsc();
	List<Product> findAllByOrderByPriceAsc();
}
