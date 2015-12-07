package com.gophergroceries.results;

import com.gophergroceries.model.dao.ConfirmedOrderSummary;

public class ConfirmedOrderSummaryResult extends ErrorResult {

	private ConfirmedOrderSummary confirmedOrderSummary;

	public ConfirmedOrderSummary getConfirmedOrderSummary() {
		return confirmedOrderSummary;
	}

	public void setConfirmedOrderSummary(ConfirmedOrderSummary confirmedOrderSummary) {
		this.confirmedOrderSummary = confirmedOrderSummary;
	}

}
