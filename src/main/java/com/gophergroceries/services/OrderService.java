package com.gophergroceries.services;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.model.Order;
import com.gophergroceries.model.dao.OrderSummary;
import com.gophergroceries.model.entities.OrderLinesEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.AddToOrderResult;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private Order order;

	@Autowired
	private OrdersRepository ordersRepository;

	private final String SESSION_ID = "TestSessionID";
	// private final String EMAIL = "camroe@gmail.com";
	// private final String PAYPAL_CONFIRMATION = "Test PayPal Confirmation";
	private final String CONFIRMATION_ID = "TestConfirmation";

	public OrdersEntity getOrderWithSessionID(String sessionid) {
		logger.debug("Called with session id " + sessionid + ", but using " + SESSION_ID);
		sessionid = SESSION_ID;
		return ordersRepository.findOneBySessionID(sessionid);
	}

	public OrdersEntity getOrderWithConfirmationID(String confirmationID) {
		logger.debug("Called with confirmationID " + confirmationID + ", but using " + CONFIRMATION_ID);
		confirmationID = CONFIRMATION_ID;
		return ordersRepository.findOneByConfirmationID(confirmationID);
	}

	public OrdersEntity getOrderWithUserName(String username) {
		return ordersRepository.findOneByUsername(username);
	}

	public AddToOrderResult addItemToOrder(AddToCartForm atcf) {
		OrdersEntity orderEntity = null;
		if (atcf.getUsername().equals("anonymousUser")) {
			orderEntity = getOrderWithSessionID(atcf.getSessionID());
		}
		else {
			orderEntity = getOrderWithUserName(atcf.getUsername());
		}
		if (null == orderEntity) {
			return createNewOrder(atcf);
		}
		return addToExisting(orderEntity, atcf);
	}

	private AddToOrderResult addToExisting(OrdersEntity orderEntity, AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		order.setOrderEntity(orderEntity);
		if (!order.add(atcf)) {
			logger.warn("Product Not Found: actf = " + atcf.toString());
			ator.setError(true);
			ator.setErrorMsg("Attempted to add unknown product to cart");
		}
		else {
			OrderSummary os = new OrderSummary(order);
			ator.setOrderSummary(os);
		}
		return ator;
	}

	private AddToOrderResult createNewOrder(AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		// Set up new OrdersEntity
		OrdersEntity oe = new OrdersEntity();
		// TODO: Get and Set EMail if available
		oe.setSessionID(atcf.getSessionID());
		oe.setUsername(atcf.getUsername());
		oe.setItems(new HashSet<OrderLinesEntity>()); // Empty
		// Set up new OrderLinesEntity
		order.setOrderEntity(oe);

		// Try to add atcf to order.
		if (order.add(atcf)) {
		}
		else
			ator.setError(true);
		ator.setErrorMsg("Error Adding to Cart. We are looking into it.");
		logger.error("Failed to add to cart in OrderService : atcf =>" + atcf);
		;
		return ator;
	}

}
