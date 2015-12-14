package com.gophergroceries.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieMgr {
	private static final Logger logger = LoggerFactory.getLogger(CookieMgr.class);

	public static String prettyPrint(Cookie cookie) {
		StringBuilder sb = new StringBuilder(100);
		if (!(cookie == null)) {
			sb.append("\n")
					.append("\n")
					.append("Name: " + cookie.getName())
					.append("\n")
					.append("Domain: " + cookie.getDomain())
					.append("\n")
					.append("Version: " + cookie.getVersion())
					.append("\n")
					.append("MaxAged: " + cookie.getMaxAge())
					.append("\n")
					.append("Value: " + cookie.getValue())
					.append("\n")
					.append("Path: " + cookie.getPath())
					.append("\n")
					.append("IsSecure: " + cookie.getSecure())
					.append("\n")
					.append("Comment: " + cookie.getComment())
					.append("\n");
		}
		return sb.toString();
	}

	public static boolean isCookieIn(String cookieName, HttpServletRequest httpServletRequest) {
		boolean found = false;
		Cookie[] cookies = httpServletRequest.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String cName = cookie.getName();
			if (cookieName.equals(cName)) {
				found = true;
			}
		}
		return found;
	}

	public static Cookie getCookie(String cookieName, HttpServletRequest httpServletRequest) {
		Cookie returnCookie = null;
		int count = 0;
		Cookie[] cookies = httpServletRequest.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cName = cookie.getName();
				if (cookieName.equals(cName)) {
					returnCookie = cookie;
					count = count + 1;
					logger.info(prettyPrint(cookie));
				}
			}
		}
		if (count > 1) {
			// more than one cookie was found with the same name (can have different
			// paths)
			logger.warn(count + " Cookies were found in the request with the same name " + cookieName);
			logger.warn("Returning the Following Cookie" + prettyPrint(returnCookie));
		}
		return returnCookie;
	}

	public static String cookieNames(HttpServletRequest httpServletRequest) {
		Cookie[] cookies = httpServletRequest.getCookies();
		int count = 0;
		StringBuilder sb = new StringBuilder(200);
		sb.append("\n");
		if (!(null == cookies)) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				count = count + 1;
				sb.append(count)
						.append(". ")
						.append(cookie.getName())
						.append("\n");
			}
			sb.append(count)
					.append(" cookies Listed")
					.append("\n");
		}
		return sb.toString();
	}
}
