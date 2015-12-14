package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gophergroceries.controllers.DeliveryController;

public class GopherCookieFactory {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	public static Cookie createCookie() {
		Cookie cookie = new Cookie(GopherCookie.GOPHER_COOKIE_NAME, GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE);
		cookie.setComment("This cookie is to save the user's cart between sessions.");
		cookie.setPath("/"); // EVERYWHERE ON THE SITE.
		cookie.setMaxAge(60 * 60 * 24 * 365); // Max age in seconds.
		logger.info("Factory Created COOKIE: " + CookieMgr.prettyPrint(cookie));
		return cookie;
	}

	public static Cookie clearCookie(Cookie cookiedToClear) {
		if (null != cookiedToClear) {
			cookiedToClear.setMaxAge(0);
		}
		return cookiedToClear;
	}
}
