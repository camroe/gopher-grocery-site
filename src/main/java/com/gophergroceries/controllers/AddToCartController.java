package com.gophergroceries.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.results.AddToOrderResult;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.OrderService;

@Controller
@RequestMapping("/v1/addtocart")
public class AddToCartController {
	private static final Logger logger = LoggerFactory.getLogger(AddToCartController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getCart() {
		return ("This is the Get Cart PAGE");
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AddToOrderResult greetingSubmit(@RequestParam("quantity") String quantity,
			@RequestParam("cartkey") String cartkey, @RequestParam("id") String id, @RequestParam("sku") String sku) {
		AddToCartForm atcf = new AddToCartForm();
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		atcf.setUsername(name);
		atcf.setCartkey(cartkey);
		atcf.setId(id);
		atcf.setQuantity(quantity);
		atcf.setSku(sku);
		atcf.setSessionID(session);
		logger.info(atcf.toString());
		return (orderService.addItemToOrder(atcf));
	}

	// v1/addtocart/ordersummary
	@RequestMapping(value = "/ordersummary", method = RequestMethod.GET)
	public @ResponseBody OrderSummaryResult getOrderSummary() {
		return orderService.getOrderSummary();
	}

}
