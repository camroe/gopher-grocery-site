package com.gophergroceries.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderlines")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrderLinesEntity implements Comparable<OrderLinesEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/*
	 * The @ManyToOne side is always the owner of the relationship. There is no
	 * way to use the mapped by attribute inside the @ManyToOne annotation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_FK")
	@JsonBackReference
	private OrdersEntity order;

	@ManyToOne()
	@JoinColumn(name = "product_FK")
	// @JsonManagedReference
	private ProductEntity product; // Foreign key to product table for specific
	// product;

	private Integer quantity;

	private BigDecimal price; // need price here to record the price that the
														// customer saw.

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdersEntity getOrder() {
		return order;
	}

	public void setOrder(OrdersEntity order) {
		this.order = order;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int compareTo(OrderLinesEntity o) {
		return (this.getProduct().getName().compareTo(o.getProduct().getName()));
	}

}
