package com.emp.rest.basic.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1000)  
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{
	
	// add a reference to our security data source
	
		@Autowired
		private DataSource securityDataSource;
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			// Using jdbc authentication
			
			auth.jdbcAuthentication().dataSource(securityDataSource);
			
		}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()	
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		//.antMatchers(HttpMethod.POST,"/jpa/register/createUser").permitAll()
		//.antMatchers(HttpMethod.GET,"/customer/list").permitAll()
				.anyRequest().authenticated()
				.and()
			//.formLogin().and()
			.httpBasic();
	}
	
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and()
//		// starts authorizing configurations
//		.authorizeRequests()
//		// ignoring the guest's urls "
//		.antMatchers("/account/register","/account/login","/logout").permitAll()
//		// authenticate all remaining URLS
//		.anyRequest().fullyAuthenticated().and()
//      /* "/logout" will log the user out by invalidating the HTTP Session,
//       * cleaning up any {link rememberMe()} authentication that was configured, */
//		.logout()
//        .permitAll()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//        .and()
//		// enabling the basic authentication
//		.httpBasic().and()
//		// configuring the session on the server
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
//		// disabling the CSRF - Cross Site Request Forgery
//		.csrf().disable();
//	}

	
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager; 
	}
}
