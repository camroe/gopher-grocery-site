package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.OrderSummaryResult;

@Service
public class DeliveryService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

	@Autowired
	private OrdersRepository ordersRepository;

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
}
