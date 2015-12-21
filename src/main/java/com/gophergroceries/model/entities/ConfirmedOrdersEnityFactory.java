package com.gophergroceries.model.entities;

import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

public class ConfirmedOrdersEnityFactory {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrdersEnityFactory.class);

	public static ConfirmedOrdersEntity createBasedOn(OrdersEntity oe, String methodOfPayment) {
		ConfirmedOrdersEntity coe = ConfirmedOrdersEnityFactory.empty();
		coe.setCity(oe.getCity());
		coe.setComment(oe.getComment());
		// Set ConfirmationID to the original OrderID.
		coe.setConfirmationID(generateConfirmationID(oe));
		coe.setCreationdate(oe.getCreationdate());
		coe.setEmail(oe.getEmail());
		coe.setFirstname(oe.getFirstname());
		// No need to set id
		coe.setLastname(oe.getLastname());
		coe.setLocation(oe.getLocation());
		coe.setPhone(oe.getPhone());
		// No need to set session
		coe.setState(oe.getState());
		coe.setUnit(oe.getUnit());
		// No need to set userName
		coe.setZipcode(oe.getZipcode());
		coe.setCheckindate(oe.getCheckindate());
		coe.setPaymenttype(methodOfPayment);
		coe.setCartid(oe.getId());

		for (OrderLinesEntity ole : oe.getOrderlines()) {
			ConfirmedOrderLinesEntity cole = new ConfirmedOrderLinesEntity();
			cole.setPrice(ole.getPrice());
			cole.setProduct(ole.getProduct());
			cole.setQuantity(ole.getQuantity());
			cole.setConfirmedorder(coe);
			SortedSet<ConfirmedOrderLinesEntity> set = coe.getOrderlines();
			set.add(cole);
			coe.setOrderlines(set);
		}
		return coe;
	}

	private static String generateConfirmationID(OrdersEntity oe) {
		// Confirmation ID is oe.id and the last 4 of the session id
		StringBuilder sb = new StringBuilder();
		String sessionid = oe.getSessionID();
		String last4ofSessionID = (sessionid == null || sessionid.length() < 3 ? sessionid
				: sessionid.substring(sessionid.length() - 4));
		logger.debug("Generating ConfirmationID from OrderEntity_ID:{} and last 4 of Session_ID: {}", oe.getId(),
				last4ofSessionID);
		sb.append(oe.getId().toString());
		sb.append(last4ofSessionID);
		return sb.toString();
	}

	public static ConfirmedOrdersEntity empty() {
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		ConfirmedOrdersEntity coe = new ConfirmedOrdersEntity();
		coe.setSessionID(session);
		coe.setUsername(userName);
		coe.setOrderlines(new TreeSet<ConfirmedOrderLinesEntity>()); // Empty
		return coe;
	}
}
