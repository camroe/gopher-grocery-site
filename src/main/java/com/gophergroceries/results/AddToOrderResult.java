package com.gophergroceries.results;

import com.gophergroceries.model.dao.OrderSummary;

public class AddToOrderResult extends ErrorResult {

	private OrderSummary orderSummary;

	public OrderSummary getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb
				.append("Number of Items- ")
				.append(orderSummary.getNumberOfItems())
				.append(":Total- ")
				.append(orderSummary.getTotal().toString())
				.append(":Session- ")
				.append(orderSummary.getOrder().getOrderEntity().getSessionID())
				.append(":Username- ")
				.append(orderSummary.getOrder().getOrderEntity().getUsername());
		return sb.toString();
	}

}
