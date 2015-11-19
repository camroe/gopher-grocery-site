package com.gophergroceries.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.results.AddToOrderResult;
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
		logger.info("Session: " + session);
		atcf.setCartkey(cartkey);
		atcf.setId(id);
		atcf.setQuantity(quantity);
		atcf.setSku(sku);
		atcf.setSessionID(session);
		logger.info(atcf.toString());
		System.out.println(atcf.toString());
		AddToOrderResult ator = orderService.addItemToOrder(atcf);
		// return a random cart count for now.
		// return (ThreadLocalRandom.current().nextInt(1, 99 + 1));
		return (ator);
	}

}
