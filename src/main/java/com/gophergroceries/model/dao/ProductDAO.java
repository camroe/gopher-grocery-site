package com.gophergroceries.model.dao;

import com.gophergroceries.model.Product;

/**
 * This class represents the methods to read/write a single Product
 * 
 * @author camroe
 *
 */
public class ProductDAO {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ProductDAO.class);

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductJson() {
		return JsonUtils.JsonStringFromObject(this.product);
	}
}
