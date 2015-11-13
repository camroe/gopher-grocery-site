package com.gophergroceries.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gophergroceries.model.AddToCartForm;

@Controller
@RequestMapping("/v1/addtocart")
public class AddToCartController {
	private static final Logger logger = LoggerFactory.getLogger(AddToCartController.class);

	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getCart() {
		return ("This is the Get Cart PAGE");
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody void greetingSubmit(@RequestParam("quantity") String quantity,
			@RequestParam("cartkey") String cartkey, @RequestParam("id") String id, @RequestParam("sku") String sku) {
		AddToCartForm atcf = new AddToCartForm();
		atcf.setCartkey(cartkey);
		atcf.setId(id);
		atcf.setQuantity(quantity);
		atcf.setSku(sku);
		logger.info(atcf.toString());
		System.out.println(atcf.toString());

	}

}
