package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.dao.ConfirmedOrderSummary;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;
import com.gophergroceries.model.repository.ConfirmedOrdersRepository;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;

@Service
public class ConfirmedOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderService.class);

	@Autowired
	private ConfirmedOrdersRepository confirmedOrdersRepository;

	public ConfirmedOrderSummaryResult getConfirmedOrderWithConfirmationID(String confirmationid) {

		ConfirmedOrderSummaryResult cosr = new ConfirmedOrderSummaryResult();
		ConfirmedOrdersEntity coe = null;

		coe = confirmedOrdersRepository.findOneByConfirmationId(confirmationid);
		if (coe == null) {
			// Nothing returned - can't find confirmed order
			// Default cosr is error
			cosr.setErrorMsg("Could not find Confirmed Order for Confirmation ID: " + confirmationid);
			logger.warn("Could not find Confirmed Order for Confirmation ID: " + confirmationid);
		}
		cosr.setError(null == coe);
		cosr.setConfirmedOrderSummary(new ConfirmedOrderSummary(coe));
		return cosr;
	}
}
