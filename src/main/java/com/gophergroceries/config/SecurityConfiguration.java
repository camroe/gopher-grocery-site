package com.gophergroceries.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("gophergroceriesMySQL")
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().and().authorizeRequests().antMatchers("**/bread")
				.authenticated().and().httpBasic().and().logout().logoutSuccessUrl("/");
	}

	// IN MEMORY FOR TESTING AND DEBUGGING
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
	// .password("password").roles("USER", "ADMIN");
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

}
