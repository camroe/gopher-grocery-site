package com.gophergroceries.model;

import java.math.BigDecimal;

public class Product {
	
	private String category;
	private String subCategory;
	private String name;
	private String img;
	private String sku;
	private String description;
	private BigDecimal price;
	
	/* Constructors for various ways to construct a product object */
	
	public Product(String JsonString) {
		
	}
	
	/* GETTERS AND SETTERS */
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	 

}
