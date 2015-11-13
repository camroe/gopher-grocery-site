//package com.gophergroceries.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("gophergroceriesMySQL")
//	DataSource dataSource;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// http.csrf().disable().formLogin().and().authorizeRequests().antMatchers("**/bread")
//		// .authenticated().and().httpBasic().and().logout().logoutSuccessUrl("/");
//
//		// http.authorizeRequests().antMatchers("/**").hasRole("user").and().formLogin();
//
//		/*
//		 * FROM:
//		 * http://stackoverflow.com/questions/25879056/expose-an-unsecure-restful-
//		 * endpoint-in-a-springboot-secured-application http .authorizeRequests()
//		 * .antMatchers("/").permitAll() .antMatchers("/signup").permitAll()
//		 * .antMatchers("/reset").permitAll()
//		 * .antMatchers("/api/notify").permitAll() .anyRequest().authenticated();
//		 * http .formLogin() .defaultSuccessUrl("/home") .failureUrl("/login?error")
//		 * .loginPage("/login") .permitAll() .and() .logout() .permitAll();
//		 */
//		http
//				.authorizeRequests()
//				.anyRequest().authenticated();
//		http.csrf().disable();
//		http.formLogin();
//
//	}
//
//	// IN MEMORY FOR TESTING AND DEBUGGING
//	@Override
//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("user").and().withUser("admin")
//				.password("password").roles("user", "admin");
//	}
//
//	// @Override
//	// protected void configure(AuthenticationManagerBuilder auth) throws
//	// Exception {
//	// auth.jdbcAuthentication().dataSource(dataSource);
//	// }
//
//}
