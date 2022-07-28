package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.services.JwtInterceptorService;

@Component
public class JwtInterceptorConfig extends WebMvcConfigurerAdapter{

	@Autowired
	JwtInterceptorService jwtInterceptorService;  

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptorService).excludePathPatterns("/api/token").addPathPatterns("/**");
	}
}
