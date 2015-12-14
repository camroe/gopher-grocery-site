package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;

public class GopherCookie {

	public static final String GOPHER_COOKIE_NAME = "gopher";
	public static final String GOPHER_COOKIE_NAME_HOLDING_VALUE = "gopher-groceries";
	private Cookie cookie = null;

	public GopherCookie(Cookie cookie) {
		this.cookie = cookie;
	}

	public void setValue(Integer cartid) {
		this.cookie.setValue(encryptedValue(cartid.toString()));
	}

	public Cookie getCookie() {
		if (null != this.cookie) {
			// reset the max age on each get of the cookie
			this.cookie.setMaxAge(60 * 60 * 24 * 365);
		}
		return this.cookie;
	}

	public String getCookieValue() {
		if (null != this.cookie) {
			// TODO: Put Decryption here.s
			return this.cookie.getValue();
		} else {
			return GOPHER_COOKIE_NAME_HOLDING_VALUE;
		}
	}

	private String encryptedValue(String string) {
		// TODO: Put encryption here.
		return string;
	}
}
