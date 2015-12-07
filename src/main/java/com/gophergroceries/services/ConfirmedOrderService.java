package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.model.dao.ConfirmedOrderSummary;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;
import com.gophergroceries.model.repository.ConfirmedOrdersRepository;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;

@Service
public class ConfirmedOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderService.class);

	@Autowired
	private ConfirmedOrdersRepository confirmedOrdersRepository;

	public ConfirmedOrderSummaryResult getConfirmedOrder() {
		// get from the confirmed order table based on username or SessionID
		ConfirmedOrderSummaryResult cosr = new ConfirmedOrderSummaryResult();
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		ConfirmedOrdersEntity coe = null;
		if (name == "anonymousUser") {
			coe = confirmedOrdersRepository.findOneBySessionID(session);
			if (coe == null) {
				// Nothing returned - can't find confirmed order
				// Default cosr is error
				cosr.setErrorMsg("Could not find Confirmed Order for Session" + session
						+ ". Has your guest session timed out? Try getting your order using your Confirmation Identification Number");
				logger.warn("Could not find Confirmed Order for Session" + session);
			}
		} else {
			// TODO: WHat happens when there is more than one confirmedOrder for this
			// user?
			coe = confirmedOrdersRepository.findOneByUsername(name);
			if (coe == null) {
				// Nothing returned - can't find confirmed order
				// Default cosr is error
				cosr.setErrorMsg("Could not find Confirmed Order for " + name);
				logger.warn("Could not find Confirmed Order for " + name);
			}
		}
		cosr.setError(null == coe);
		cosr.setConfirmedOrderSummary(new ConfirmedOrderSummary(coe));
		return cosr;
	}
}
