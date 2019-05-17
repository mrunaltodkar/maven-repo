package com.capgemini.spring.configuration;

import com.capgemini.spring.renderer.*;
import com.capgemini.spring.provider.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration{
	@Bean
	public MessageProvider provider() {
		return new HwMessageProvider();
	}
 
	@Bean
	public MessageRenderer renderer(){
		return new MessageRenderer(provider());
 	}
}