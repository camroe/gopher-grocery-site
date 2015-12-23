package com.gophergroceries.model.dao;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gophergroceries.model.entities.ConfirmedOrderLinesEntity;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;

public class ConfirmedOrderSummary {

	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderSummary.class);

	private BigDecimal total = new BigDecimal(0);
	private Integer numberOfItems = new Integer(0);
	private ConfirmedOrdersEntity confirmedOrdersEntity;
	private BigDecimal groceryTotal = new BigDecimal(0);
	private BigDecimal serviceFee = new BigDecimal(0);

	public ConfirmedOrderSummary(ConfirmedOrdersEntity confirmedOrdersEntity) {
		this.confirmedOrdersEntity = confirmedOrdersEntity;
		recalculate();
		if (total.compareTo(BigDecimal.ZERO) == 0) {
			logger.trace("Confirmed Order Summary Constructed with '0' total");
		}
		if (numberOfItems.intValue() == 0) {
			logger.trace("Confirmed Order Summary Constructed with '0' items");
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

	public ConfirmedOrdersEntity getconfirmedOrdersEntity() {
		return confirmedOrdersEntity;
	}

	public void setCoe(ConfirmedOrdersEntity coe) {
		this.confirmedOrdersEntity = coe;
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
		if (!(null == this.confirmedOrdersEntity)) {
			this.numberOfItems = calcNumberOfItems(this.confirmedOrdersEntity);
			this.groceryTotal = calcGroceryTotal(this.confirmedOrdersEntity);
			this.serviceFee = calcServiceFee(this.confirmedOrdersEntity);
			this.total = groceryTotal.add(serviceFee);
		}
	}

	private BigDecimal calcServiceFee(ConfirmedOrdersEntity confirmedOrdersEntity) {
		BigDecimal serviceFee = new BigDecimal(OrderSummary.MINIMUM_SERVICE_FEE.doubleValue());
		BigDecimal groceries = calcGroceryTotal(confirmedOrdersEntity);
		if (serviceFee.doubleValue() < (OrderSummary.SERVICE_FEE_PERCENTAGE.multiply(groceries)).doubleValue()) {
			serviceFee = OrderSummary.SERVICE_FEE_PERCENTAGE.multiply(groceries);
		}
		return serviceFee;
	}

	private Integer calcNumberOfItems(ConfirmedOrdersEntity coe) {
		Integer numberOfItems = 0;
		for (ConfirmedOrderLinesEntity cole : coe.getOrderlines()) {
			numberOfItems = numberOfItems + cole.getQuantity();
		}
		return numberOfItems;
	}

	private BigDecimal calcGroceryTotal(ConfirmedOrdersEntity coe) {
		BigDecimal runningTotal = new BigDecimal(0);
		for (ConfirmedOrderLinesEntity cole : coe.getOrderlines()) {

			runningTotal = runningTotal
					.add((cole.getPrice()
							.multiply(new BigDecimal(cole.getQuantity()))));
		}
		return runningTotal;
	}
}
