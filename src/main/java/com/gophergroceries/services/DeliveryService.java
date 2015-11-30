package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.model.entities.ConfirmedOrdersEnityFactory;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.repository.ConfirmedOrdersRepository;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.OrderSummaryResult;

@Service
public class DeliveryService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ConfirmedOrdersRepository confirmedOrdersRepository;
	
	@Autowired
	private OrderService orderService;

	
	
	public OrderSummaryResult setDeliveryInformation(
			String firstName,
			String lastName,
			String location,
			String unit,
			String phone,
			String email,
			String comment) {
		// TODO: For now assume all is valid.
		OrderSummaryResult osr = orderService.getOrderSummary();
		OrdersEntity oe = osr.getOrderSummary().getOrder().getOrderEntity();
		oe.setCity("Salt Lake City");
		oe.setZipCode("84121");
		oe.setState("Ut");
		oe.setFirstName(firstName);
		oe.setLastName(lastName);
		oe.setLocation(location);
		oe.setUnit(unit);
		oe.setPhone(phone);
		oe.setEmail(email);
		oe.setComment(comment);
		ordersRepository.saveAndFlush(oe);
		osr.setError(false);
		osr.setErrorMsg("Delivery Set");
		logger.trace("DeliveryService called");
		return osr;
	}

	public boolean transferOrderToSubmitted() {
		boolean result = false;
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName(); // get logged in username
		OrdersEntity oe = null;
		if (userName != "anonymousUser") {
			oe = ordersRepository.findOneByUsername(userName);
		}
		else {
			oe = ordersRepository.findOneBySessionID(session);
		}
		ConfirmedOrdersEntity coe = moveToConfirmed(oe);
		confirmedOrdersRepository.saveAndFlush(coe);
		ordersRepository.delete(oe.getId());
		result=true;
		return result;
	}

	private ConfirmedOrdersEntity moveToConfirmed(OrdersEntity oe) {
		return ConfirmedOrdersEnityFactory.createBasedOn(oe);
	}
}
