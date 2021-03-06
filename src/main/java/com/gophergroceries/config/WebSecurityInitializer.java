package com.gophergroceries.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer implements
 * WebApplication-Initializer, so it will be discovered by Spring and be used to
 * register DelegatingFilterProxy with the web container. Although you can
 * override its appendFilters() or insertFilters() methods to register filters
 * of your own choosing, you need not override anything to register
 * DelegatingFilterProxy.
 * 
 * Whether you configure DelegatingFilterProxy in web.xml or by subclassing
 * AbstractSecurityWebApplicationInitializer, it will intercept requests coming
 * into the application and delegate them to a bean whose ID is
 * springSecurityFilterChain.
 * 
 * As for the springSecurityFilterChain bean itself, it’s another special filter
 * known as FilterChainProxy. It’s a single filter that chains together one or
 * more additional filters. Spring Security relies on several servlet filters to
 * provide different security features, but you should almost never need to know
 * these details, as you likely won’t need to explicitly declare the
 * springSecurityFilterChain bean or any of the filters it chains together.
 * Those filters will be created when you enable web security.
 * 
 * Excerpt From: “Spring.in.Action.4.edition.epub.” iBooks.
 * 
 * @author camroe
 *
 */
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class };
	}
}