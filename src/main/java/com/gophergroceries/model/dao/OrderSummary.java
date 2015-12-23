package com.gophergroceries.model.dao;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gophergroceries.model.Order;
import com.gophergroceries.model.entities.OrderLinesEntity;

public class OrderSummary {

	private static final Logger logger = LoggerFactory.getLogger(OrderSummary.class);

	private BigDecimal total = new BigDecimal(0);
	private Integer numberOfItems = new Integer(0);
	private Order order;
	private BigDecimal groceryTotal = new BigDecimal(0);
	private BigDecimal serviceFee = new BigDecimal(0);
	public static final BigDecimal SERVICE_FEE_PERCENTAGE = new BigDecimal(0.2);
	public static final BigDecimal MINIMUM_SERVICE_FEE = new BigDecimal(20);

	public OrderSummary(Order order) {
		this.order = order;
		recalculate();
		if (total.compareTo(BigDecimal.ZERO) == 0) {
			logger.trace("Order Summary Constructed with '0' total");

		}
		if (numberOfItems.intValue() == 0) {
			logger.trace("Order Summary Constructed with '0' items");
		}
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getGroceryTotal() {
		return groceryTotal;
	}

	public void setGroceryTotal(BigDecimal groceryTotal) {
		this.groceryTotal = groceryTotal;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public void recalculate() {
		if (!(null == this.order)) {
			this.numberOfItems = calcNumberOfItems(this.order);
			this.groceryTotal = calcGroceryTotal(this.order);
			this.serviceFee = calcServiceFee(this.order);
			this.total = groceryTotal.add(serviceFee);
		}
	}

	private Integer calcNumberOfItems(Order order) {
		Integer numberOfItems = 0;
		for (OrderLinesEntity ole : order.getOrderEntity().getOrderlines()) {
			numberOfItems = numberOfItems + ole.getQuantity();
		}
		return numberOfItems;
	}

	private BigDecimal calcServiceFee(Order order) {
		BigDecimal serviceFee = new BigDecimal(OrderSummary.MINIMUM_SERVICE_FEE.doubleValue());
		BigDecimal groceries = calcGroceryTotal(order);
		if (serviceFee.doubleValue() < (OrderSummary.SERVICE_FEE_PERCENTAGE.multiply(groceries)).doubleValue()) {
			serviceFee = OrderSummary.SERVICE_FEE_PERCENTAGE.multiply(groceries);
		}
		return serviceFee;
	}

	private BigDecimal calcGroceryTotal(Order order) {
		BigDecimal runningTotal = new BigDecimal(0);
		for (OrderLinesEntity ole : order.getOrderEntity().getOrderlines()) {

			runningTotal = runningTotal
					.add((ole.getPrice()
							.multiply(new BigDecimal(ole.getQuantity()))));
		}

		return runningTotal;
	}
}
