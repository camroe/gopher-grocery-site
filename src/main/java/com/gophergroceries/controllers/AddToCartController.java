package com.gophergroceries.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.gophergroceries.cookies.CookieMgr;
import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.cookies.GopherCookieFactory;
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
	public @ResponseBody AddToOrderResult addToCart(
			@RequestParam("quantity") String quantity,
			@RequestParam("cartkey") String cartkey,
			@RequestParam("id") String id, // note this is product id
			@RequestParam("sku") String sku,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		AddToCartForm atcf = new AddToCartForm();
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		atcf.setUsername(name);
		atcf.setCartkey(gopherCookie.getCookieValue());
		atcf.setId(id);
		atcf.setQuantity(quantity);
		atcf.setSku(sku);
		atcf.setSessionID(session);
		logger.info(atcf.toString());
		AddToOrderResult ator = orderService.addItemToOrder(atcf);
		if (gopherCookie.getCookie() == null) {
			gopherCookie = new GopherCookie(GopherCookieFactory.createCookie());
			gopherCookie.setValue(new Integer(ator.getOrderSummary().getOrder().getOrderEntity().getId()));
			httpServletResponse.addCookie(gopherCookie.getCookie());
		}
		return (ator);
	}

	// v1/addtocart/ordersummary
	@RequestMapping(value = "/ordersummary", method = RequestMethod.GET)
	public @ResponseBody OrderSummaryResult getOrderSummary(
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		return orderService.getOrderSummary(gopherCookie);
	}

}
