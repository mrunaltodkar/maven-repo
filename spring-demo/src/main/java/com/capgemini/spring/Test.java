package com.capgemini.spring.test;

import com.capgemini.spring.renderer.MessageRenderer;
import com.capgemini.spring.configuration.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test{
	public static void main(String args[]){
		ApplicationContext context= new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		//ApplicationContext context= new AnnotationConfigApplicationContext(GoodMorningConfiguration.class);
		MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
 		renderer.render();

		MessageRenderer renderer1 = (MessageRenderer)context.getBean("renderer");
		
		//To check they are singleton or not
		System.out.println(renderer.hashCode());
		System.out.println(renderer1.hashCode());
	}

}