package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.CustomUserDetailService;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	CustomUserDetailService customuserdetailService;

	@Autowired
	JwtAuthenticationEntryPoint entryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customuserdetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				.csrf().disable().cors().disable().authorizeRequests().antMatchers("/api/token").permitAll()
				.antMatchers("/image/**").permitAll().antMatchers("/v2/api-docs", "/swagger-resources",
						"/swagger-resources/configuration/ui", "/swagger-resources/configuration/security")
				.permitAll()
//				.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("admin", "user")
//				.antMatchers(HttpMethod.POST, "/api/**").hasAnyRole("admin", "user")
//				.antMatchers(HttpMethod.PUT, "/api/**").hasRole("admin").antMatchers(HttpMethod.DELETE, "/api/**")
//				.hasRole("admin").anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling();

	}

	// encoding of password
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
