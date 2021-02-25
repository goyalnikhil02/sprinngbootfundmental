package com.example.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.test.interceptor.MyCustomInterceptor;
@Component
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	MyCustomInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}

}