package com.gophergroceries.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gophergroceries.cookies.CookieMgr;
import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.CategoryMappingService;
import com.gophergroceries.services.OrderService;
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
	
	@Autowired 
	private OrderService orderService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale,
			Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String hostname = httpServletRequest.getHeader("Host");
		logger.trace("HOST:" + hostname);
		String formattedDate = dateFormat.format(date);
		logger.trace("COOKIES: " + CookieMgr.cookieNames(httpServletRequest));
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		OrderSummaryResult osr = orderService.getOrderSummary(gopherCookie);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("catMap", catMap.getCategoryList());
		model.addAttribute("popularProducts", productService.getPopularProducts());
		model.addAttribute("items", osr.getOrderSummary().getNumberOfItems());
		// This maps to webapp/WEB-INF/views/home.jsp based on config in
		// servlet-context.xml
		return "home";
	}

	

	// @Secured("USER")
	@RequestMapping(value = "/*ot*et*mplemented*", method = RequestMethod.GET)
	public String notYetImplemented() {
		return "notyetimplemented";
	}

}
