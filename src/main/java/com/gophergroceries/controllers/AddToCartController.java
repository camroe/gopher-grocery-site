package com.gophergroceries.controllers;

import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

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
	public @ResponseBody Integer greetingSubmit(@RequestParam("quantity") String quantity,
			@RequestParam("cartkey") String cartkey, @RequestParam("id") String id, @RequestParam("sku") String sku,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		String sessionID = getSessionID();
		String userName = getUserName();
		logger.info("Add tocart called with Username : " + userName + "[" + sessionID + "]");
		processCookies(request, sessionID, response);

		AddToCartForm atcf = new AddToCartForm();
		atcf.setCartkey(cartkey);
		atcf.setId(id);
		atcf.setQuantity(quantity);
		atcf.setSku(sku);
		logger.info(atcf.toString());
		System.out.println(atcf.toString());

		model.addAttribute("userName", userName);
		// return a random cart count for now.
		return (ThreadLocalRandom.current().nextInt(1, 99 + 1));
	}

	private String getSessionID() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}

	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		return name;
	}

	private void processCookies(HttpServletRequest request, String sessionID, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		int count = 1;
		for (Cookie cookie : cookies) {
			if (cookie.getName() == sessionID) {
				System.out.println("IsSecure: " + cookie.getSecure());
				System.out.println(count++ + ". Name: " + cookie.getName());
			}
			else {
				System.out.println(count++ + ". Name: " + cookie.getName());
			}
		}
	}

}