package com.gophergroceries.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.entities.ProductEntity;
import com.gophergroceries.model.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productRepository;

	public List<ProductEntity> getPopularProducts() {
		return productRepository.findByPopularIgnoreCase("Y");
	}

	public List<ProductEntity> getProductsInCategory(String category) {
		return productRepository.findByCategoryOrderByNameAsc(category);
	}
	
	public List<ProductEntity> getProducts() {
		return productRepository.findAllByOrderByNameAsc();
	}
	
	public ProductEntity getProduct(Integer id) {
		return productRepository.findOne(id);
	}
	
}
