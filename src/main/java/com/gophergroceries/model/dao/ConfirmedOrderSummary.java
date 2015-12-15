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

	public ConfirmedOrderSummary(ConfirmedOrdersEntity confirmedOrdersEntity) {
		this.confirmedOrdersEntity = confirmedOrdersEntity;
		recalculate();
		if (total.longValueExact() == 0l) {
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

	public void recalculate() {
		if (!(null == this.confirmedOrdersEntity)) {
			this.numberOfItems = calcNumberOfItems(this.confirmedOrdersEntity);
			this.total = calcTotal(this.confirmedOrdersEntity);
		}
	}

	private Integer calcNumberOfItems(ConfirmedOrdersEntity coe) {
		Integer numberOfItems = 0;
		for (ConfirmedOrderLinesEntity cole : coe.getOrderLines()) {
			numberOfItems = numberOfItems + cole.getQuantity();
		}
		return numberOfItems;
	}

	private BigDecimal calcTotal(ConfirmedOrdersEntity coe) {
		BigDecimal runningTotal = new BigDecimal(0);
		for (ConfirmedOrderLinesEntity cole : coe.getOrderLines()) {

			runningTotal = runningTotal
					.add((cole.getPrice()
							.multiply(new BigDecimal(cole.getQuantity()))));
		}
		return runningTotal;
	}
}
