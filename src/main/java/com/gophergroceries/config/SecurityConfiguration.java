package com.gophergroceries.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// EVERYTHING COMMENTED OUT TO TEST METHOD ONLY SECURITY.
// @Configuration
// @EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// @Autowired
	// @Qualifier("gophergroceriesMySQL")
	// DataSource dataSource;

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.csrf().disable().formLogin().and().authorizeRequests().antMatchers("**/bread")
	// .authenticated().and().httpBasic().and().logout().logoutSuccessUrl("/");
	// }

	// IN MEMORY FOR TESTING AND DEBUGGING
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
	// .password("password").roles("USER", "ADMIN");
	// }

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.jdbcAuthentication().dataSource(dataSource);
	// }

}
