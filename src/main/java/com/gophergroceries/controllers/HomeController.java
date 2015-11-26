package com.gophergroceries.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gophergroceries.services.CategoryMappingService;
import com.gophergroceries.services.ProductsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CategoryMappingService catMap;

	@Autowired
	private ProductsService productService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("catMap", catMap.getCategoryList());
		model.addAttribute("popularProducts", productService.getPopularProducts());

		// This maps to webapp/WEB-INF/views/home.jsp based on config in
		// servlet-context.xml
		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public String getDelivery(Locale locale, Model model) {
		logger.info("Welcome Delivery! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		// This maps to webapp/WEB-INF/views/home.jsp based on config in
		// servlet-context.xml
		return "delivery";
	}

	@RequestMapping(value = "/delivery", method = RequestMethod.POST)
	public String postDelivery(@RequestParam("first_name") String firstName
//			,
//			@RequestParam("last_name") String lastName,
//			@RequestParam("address1") String addressLine1,
//			@RequestParam("address2") String addressLine2,
//			@RequestParam("phone") String phone,
//			@RequestParam("email") String email,
//			@RequestParam("comment") String comment,
//			@RequestParam("unit") String unit,
//			@RequestParam("address") String address
			)

	{
		logger.trace(firstName);
//		logger.trace(lastName);
//		logger.trace(addressLine1);
//		logger.trace(addressLine2);
//		logger.trace(phone);
//		logger.trace(email);
//		logger.trace(comment);
//		logger.trace(unit);
//		logger.trace(address);

		return "delivery";
	}

	@Secured("USER")
	@RequestMapping(value = "/*ot*et*mplemented*", method = RequestMethod.GET)
	public String notYetImplemented() {
		return "notyetimplemented";
	}

}
