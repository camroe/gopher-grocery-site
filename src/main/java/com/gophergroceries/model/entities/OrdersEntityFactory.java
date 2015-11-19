package com.gophergroceries.model.entities;

import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

public class OrdersEntityFactory {

	public static OrdersEntity empty() {
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		OrdersEntity oe = new OrdersEntity();
		oe.setSessionID(session);
		oe.setUsername(name);
		oe.setItems(new HashSet<OrderLinesEntity>()); // Empty
		return oe;
	}
}
