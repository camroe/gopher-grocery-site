package com.gophergroceries.results;

import com.gophergroceries.model.dao.OrderSummary;

public class OrderSummaryResult extends ErrorResult {

	private OrderSummary orderSummary;

	public OrderSummary getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}

}
