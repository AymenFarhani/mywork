package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.cors();
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/student/**").hasAnyAuthority("MANAGER","STUDENT")
		.anyRequest().authenticated()
		.and()
		.formLogin();
	}
	 
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Aymen").password("{noop}aymen").authorities("MANAGER");
		auth.inMemoryAuthentication().withUser("Spring security").password("{noop}spring").authorities("STUDENT");
	}
	 

}
