package com.gophergroceries.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gophergroceries.model.dao.OrderSummary;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.CategoryMappingService;
import com.gophergroceries.services.OrderService;

@Controller
public class OrdersController {
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private CategoryMappingService catMap;

	@RequestMapping(value = "/v1/orderAPI/orders", method = RequestMethod.GET)
	public String displayOrderPage(Locale locale, Model model) {
		logger.info("OrderPage The client locale is {}.", locale);
		OrderSummaryResult os = orderService.getOrderSummary();
		if(os.isError()){
			//TODO:Can I do something here? 
		}
			model.addAttribute("orderSummaryResult", orderService.getOrderSummary());
	// This maps to webapp/WEB-INF/views/order.jsp based on config in
		// servlet-context.xml
		return "order";
	}

}
