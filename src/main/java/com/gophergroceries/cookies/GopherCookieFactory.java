package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gophergroceries.controllers.DeliveryController;

public class GopherCookieFactory {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	private static final String domain = ""; // could be gopher-groceries.com

	public static Cookie createCookie() {
		Cookie cookie = new Cookie(GopherCookie.GOPHER_COOKIE_NAME, GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE);
		cookie.setComment("This cookie is to save the user's cart between sessions.");
		cookie.setPath("/"); // EVERYWHERE ON THE SITE.
		cookie.setMaxAge(60 * 60 * 24 * 365); // Max age in seconds.
		cookie.setDomain(domain);
		logger.info("Factory Created COOKIE: " + CookieMgr.prettyPrint(cookie));
		return cookie;
	}

	public static Cookie clearCookie(Cookie cookieToClean) {
		if (null != cookieToClean) {
			//Trying new cookie. 
			logger.trace("Clearing Cookie: " + cookieToClean.getValue());
			Cookie clearcookie = new Cookie(GopherCookie.GOPHER_COOKIE_NAME,"");
			clearcookie.setMaxAge(0);//0 means delete
			clearcookie.setValue(GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE);
			clearcookie.setPath("/");
			clearcookie.setComment("Cleared");
			clearcookie.setDomain(domain);
		}
		return cookieToClean;
	}
}
