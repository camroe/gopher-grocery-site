package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;

public class GopherCookieFactory {

	public static Cookie createCookie() {
		Cookie cookie = new Cookie(GopherCookie.GOPHER_COOKIE_NAME, GopherCookie.GOPHER_COOKIE_NAME_HOLDING_VALUE);
		cookie.setComment("This cookie is to save the user's cart between sessions.");
		cookie.setPath("/"); // EVERYWHERE ON THE SITE.
		cookie.setMaxAge(60*60*24*365); //Max age in seconds. 
		return cookie;
	}
}
