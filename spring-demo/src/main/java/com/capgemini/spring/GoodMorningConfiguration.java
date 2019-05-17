package com.capgemini.spring.configuration;

import com.capgemini.spring.renderer.*;
import com.capgemini.spring.provider.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodMorningConfiguration{
	@Bean
	public MessageProvider provider() {
		return new GmMessageProvider();
	}
 
	@Bean
	public MessageRenderer renderer(){
		return new MessageRenderer(provider());
 	}
}