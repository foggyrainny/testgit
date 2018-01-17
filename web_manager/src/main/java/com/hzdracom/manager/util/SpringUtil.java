package com.hzdracom.manager.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtil {
	private static WebApplicationContext context;
	
	public static WebApplicationContext getSpringContext() {
		if(context==null){
			context = ContextLoader.getCurrentWebApplicationContext();
		}
		return context;
	}
	
	public static ApplicationContext getSpringContextA() {
		
//		ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml");
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
		return ac;
	}
}
