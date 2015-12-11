package com.gophergroceries.model.entities;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

public class ConfirmedOrdersEnityFactory {

	public static ConfirmedOrdersEntity createBasedOn(OrdersEntity oe, String methodOfPayment) {
		ConfirmedOrdersEntity coe = ConfirmedOrdersEnityFactory.empty();
		coe.setCity(oe.getCity());
		coe.setComment(oe.getComment());
		// Set ConfirmationID to the original OrderID.
		coe.setConfirmationID(oe.getId().toString());
		coe.setCreationDate(oe.getCreationDate());
		coe.setEmail(oe.getEmail());
		coe.setFirstName(oe.getFirstName());
		// No need to set id
		coe.setLastName(oe.getLastName());
		coe.setLocation(oe.getLocation());
		coe.setPhone(oe.getPhone());
		// No need to set session
		coe.setState(oe.getState());
		coe.setUnit(oe.getUnit());
		// No need to set userName
		coe.setZipCode(oe.getZipCode());
		coe.setCheckinDate(oe.getCheckinDate());
		coe.setPaymentType(methodOfPayment);

		for (OrderLinesEntity ole : oe.getOrderLines()) {
			ConfirmedOrderLinesEntity cole = new ConfirmedOrderLinesEntity();
			cole.setPrice(ole.getPrice());
			cole.setProduct(ole.getProduct());
			cole.setQuantity(ole.getQuantity());
			cole.setConfirmedorder(coe);
			SortedSet<ConfirmedOrderLinesEntity> set = coe.getOrderLines();
			set.add(cole);
			coe.setOrderLines(set);
		}
		return coe;
	}

	public static ConfirmedOrdersEntity empty() {
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		ConfirmedOrdersEntity coe = new ConfirmedOrdersEntity();
		coe.setSessionID(session);
		coe.setUsername(userName);
		coe.setOrderLines(new TreeSet<ConfirmedOrderLinesEntity>()); // Empty
		return coe;
	}
}
