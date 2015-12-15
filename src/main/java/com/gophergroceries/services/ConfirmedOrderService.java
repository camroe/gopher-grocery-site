package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.model.dao.ConfirmedOrderSummary;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;
import com.gophergroceries.model.repository.ConfirmedOrdersRepository;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;

@Service
public class ConfirmedOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderService.class);

	@Autowired
	private ConfirmedOrdersRepository confirmedOrdersRepository;

	// public ConfirmedOrderSummaryResult getConfirmedOrder(GopherCookie
	// gopherCookie) {
	// // TODO:! Get FROM COOKIE! ... wait ... Should I ever use this method ? The
	// // Cookie should never contain the confirmed id, only the cart id.
	// // get from the confirmed order table based on username or SessionID
	// ConfirmedOrderSummaryResult cosr = new ConfirmedOrderSummaryResult();
	// // if (gopherCookie == null) {
	// // //got passed a null cookie
	// // cosr.setErrorMsg("Could not find Confirmed Order, cookie is 'null'.");
	// // logger.warn("Could not find Confirmed Order, cookie is 'null'.");
	// // return cosr;
	// // }
	// // //ok, let's try to decrypt the cookie
	// // String cartkey = gopherCookie.getCookieValue();
	// // ConfirmedOrdersEntity coe
	// // =confirmedOrdersRepository.findOneBySessionID(session);
	// // if (coe == null) {
	// // // Nothing returned - can't find confirmed order
	// // // Default cosr is error
	// // cosr.setErrorMsg("Could not find Confirmed Order for Session" + session
	// // + ". Has your guest session timed out? Try getting your order using your
	// // Confirmation Identification Number");
	// // logger.warn("Could not find Confirmed Order for Session" + session);
	// // }
	// // } else {
	// // // TODO: WHat happens when there is more than one confirmedOrder for
	// this
	// // // user?
	// // coe = confirmedOrdersRepository.findOneByUsername(name);
	// // if (coe == null) {
	// // // Nothing returned - can't find confirmed order
	// // // Default cosr is error
	// // cosr.setErrorMsg("Could not find Confirmed Order for " + name);
	// // logger.warn("Could not find Confirmed Order for " + name);
	// // }
	// // }
	// // cosr.setError(null == coe);
	// // cosr.setConfirmedOrderSummary(new ConfirmedOrderSummary(coe));
	// return cosr;
	// }

	public ConfirmedOrderSummaryResult getConfirmedOrder(String confirmationId) {
		// get from the confirmed order table based on username or SessionID
		// TODO: Is this called? If it is ... it should not be.
		ConfirmedOrderSummaryResult cosr = new ConfirmedOrderSummaryResult();
		ConfirmedOrdersEntity coe = null;
		coe = confirmedOrdersRepository.findOneByConfirmationId(confirmationId);
		if (coe == null) {
			// Nothing returned - can't find confirmed order
			// Default cosr is error
			cosr.setErrorMsg("Could not find Confirmed Order for Confirmation ID: " + confirmationId);
			logger.warn("Could not find Confirmed Order for Confirmation ID: " + confirmationId);
		}
		cosr.setError(null == coe);
		cosr.setConfirmedOrderSummary(new ConfirmedOrderSummary(coe));
		return cosr;
	}
}
