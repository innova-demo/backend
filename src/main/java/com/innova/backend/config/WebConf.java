package com.innova.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.innova.backend" })
public class WebConf extends WebMvcConfigurerAdapter {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		System.out.println("---> Enable CORS!!");
        registry.addMapping("/**")
        		.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
        		.allowedOrigins("*")
        		.allowedHeaders("*")
        		.allowCredentials(false);
	}
}
