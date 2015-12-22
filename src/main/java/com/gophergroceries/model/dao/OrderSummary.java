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

	public OrderSummary(Order order) {
		this.order = order;
		recalculate();
		if (total.compareTo(BigDecimal.ZERO) == 0) {
			logger.warn("Order Summary Constructed with '0' total");

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

	public void recalculate() {
		if (!(null == this.order)) {
			this.numberOfItems = calcNumberOfItems(this.order);
			this.total = calcTotal(this.order);
		}
	}

	private Integer calcNumberOfItems(Order order) {
		Integer numberOfItems = 0;
		for (OrderLinesEntity ole : order.getOrderEntity().getOrderlines()) {
			numberOfItems = numberOfItems + ole.getQuantity();
		}
		return numberOfItems;
	}

	private BigDecimal calcTotal(Order order) {
		BigDecimal runningTotal = new BigDecimal(0);
		for (OrderLinesEntity ole : order.getOrderEntity().getOrderlines()) {

			runningTotal = runningTotal
					.add((ole.getPrice()
							.multiply(new BigDecimal(ole.getQuantity()))));
		}

		return runningTotal;
	}
}
