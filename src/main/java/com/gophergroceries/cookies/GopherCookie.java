package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;

public class GopherCookie {

	public static final String GOPHER_COOKIE_NAME = "gopher";
	public static final String GOPHER_COOKIE_NAME_HOLDING_VALUE = "gopher-groceries";
	private Cookie cookie = null;

	public GopherCookie(Cookie cookie) {
		super();
		this.cookie = cookie;
	}

	public void setValue(Integer cartid) {
		// TODO: Put encryption here.
		this.cookie.setValue(cartid.toString());
	}

	public Cookie getCookie() {
		return this.cookie;
	}

}
