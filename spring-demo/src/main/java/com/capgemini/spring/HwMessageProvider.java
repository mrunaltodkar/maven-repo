package com.capgemini.spring.provider;

public class HwMessageProvider implements MessageProvider{
	
	@Override
	public String getMessage(){
		return "Hello World!!!";
	}

}