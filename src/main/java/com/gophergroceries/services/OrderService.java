package com.gophergroceries.services;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.model.Order;
import com.gophergroceries.model.dao.OrderSummary;
import com.gophergroceries.model.entities.OrderLinesEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.entities.OrdersEntityFactory;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.AddToOrderResult;
import com.gophergroceries.results.OrderSummaryResult;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private Order order;

	@Autowired
	private OrdersRepository ordersRepository;

	// private final String SESSION_ID = "TestSessionID";
	// private final String EMAIL = "camroe@gmail.com";

	public OrdersEntity getOrderWithSessionID(String sessionid) {
		return ordersRepository.findOneBySessionID(sessionid);
	}

	public OrdersEntity getOrderWithUserName(String username) {
		return ordersRepository.findOneByUsername(username);
	}

	public OrdersEntity getOrderWithCartKey(String cartkey) {
		if (GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE.equals(cartkey) || "".equals(cartkey)) {
			return OrdersEntityFactory.empty();
		}
		Integer cartkeyInt = 0;
		try {
			cartkeyInt = new Integer(cartkey);
		} catch (NumberFormatException nfe) {
			logger.warn("Cartkey is not a number: " + cartkey);
		}
		return ordersRepository.findOne(cartkeyInt);
	}

	public AddToOrderResult addItemToOrder(AddToCartForm atcf) {
		OrdersEntity orderEntity = null;
		if ((atcf.getCartkey() == null) || (atcf.getCartkey().equals(GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE))) {
			// No cart saved in cookie.
			return createNewOrder(atcf);
		}
		else
			orderEntity = getOrderWithCartKey(atcf.getCartkey());

		if (null == orderEntity) {
			logger.warn("Could not find Cart with ID: " + atcf.getCartkey() + " - Creating new Cart");
			return createNewOrder(atcf);
		}
		logger.info("Found Cart with ID: " + atcf.getCartkey());
		return addToExisting(orderEntity, atcf);
	}

	public OrderSummaryResult getOrderSummary(GopherCookie gopherCookie) {
		OrderSummaryResult osr = new OrderSummaryResult();

		OrdersEntity oe = getOEBasedOnCookie(gopherCookie);
		if (null == oe) {
			oe = OrdersEntityFactory.empty();
		}
		order.setOrderEntity(oe);
		OrderSummary os = new OrderSummary(order);
		osr.setError(false);
		osr.setErrorMsg("Success");
		osr.setOrderSummary(os);
		return osr;
	}

	public OrderSummaryResult updateOrder(OrdersEntity modifiedOrdersEntity) {
		OrderSummaryResult returnResult = new OrderSummaryResult();

		OrdersEntity oe = ordersRepository.getOne(modifiedOrdersEntity.getId());
		if (null == oe) {
			// order not found by id
			returnResult.setErrorMsg("Could not find Order: " + modifiedOrdersEntity.getId());
			return returnResult; // break out of method.
		}
		for (OrderLinesEntity ol : oe.getOrderlines()) {
			Integer newQuantity = findMatchingOrderLineQuantity(ol.getId(), modifiedOrdersEntity);
			logger.trace("OldQuantity: " + ol.getQuantity());
			logger.trace("NewQuantity: " + newQuantity);
			ol.setQuantity(newQuantity);
		}
		removeZeroQuantityOrderlines(oe);
		ordersRepository.saveAndFlush(oe);

		returnResult.setOrderSummary(new OrderSummary(new Order(oe)));
		returnResult.setError(false);
		returnResult.setErrorMsg("Success");
		return returnResult;

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
			ator.setError(false);
			ator.setErrorMsg("Success: Add to existing order");
			OrderSummary os = new OrderSummary(order);
			ator.setOrderSummary(os);
		}
		logger.debug("ATOR: " + ator);
		return ator;
	}

	private AddToOrderResult createNewOrder(AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		// Set up new OrdersEntity
		OrdersEntity oe = OrdersEntityFactory.empty();
		// Set up new OrderLinesEntity
		order.setOrderEntity(oe);

		// Try to add atcf to order.
		if (order.add(atcf)) {
			ator.setError(false);
			ator.setErrorMsg("Success: Create new Order");
		}
		else {
			ator.setError(true);
			ator.setErrorMsg("Error Adding to Cart. We are looking into it.");
			logger.error("Failed to add to cart in OrderService : atcf =>" + atcf);
		}
		OrderSummary os = new OrderSummary(order);
		ator.setOrderSummary(os);
		logger.debug("ATOR: " + ator);
		return ator;
	}

	// private OrdersEntity getOEBasedOn(String name, String session) {
	// if (name.equals("anonymousUser"))
	// return getOrderWithSessionID(session);
	// else
	// return getOrderWithUserName(name);
	// }

	private OrdersEntity getOEBasedOnCookie(GopherCookie gopherCookie) {
		if (gopherCookie == null) {
			// No cookie was set or cookies have been cleared.
			return null;
		}
		return getOrderWithCartKey(gopherCookie.getCookieValue());
	}

	private void removeZeroQuantityOrderlines(OrdersEntity oe) {
		SortedSet<OrderLinesEntity> setOfOLEs = oe.getOrderlines();

		Iterator<OrderLinesEntity> iterator = setOfOLEs.iterator();
		while (iterator.hasNext()) {
			OrderLinesEntity ole = iterator.next();
			if (ole.getQuantity() == 0) {
				iterator.remove();
			}
		}
		oe.setOrderlines(setOfOLEs);
	}

	private Integer findMatchingOrderLineQuantity(Integer id, OrdersEntity modifiedOrdersEntity) {
		Integer returnValue = new Integer(0);
		Set<OrderLinesEntity> setOfOLEs = modifiedOrdersEntity.getOrderlines();

		for (OrderLinesEntity ole : setOfOLEs) {
			if (ole.getId().equals(id)) {
				returnValue = new Integer(ole.getQuantity());

			}
		}
		return returnValue;
	}

}
