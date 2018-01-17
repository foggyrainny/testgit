package com.hzdracom.core.util;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;


public class SpringContextUtils
{
	
	@SuppressWarnings ("unchecked")
    public static <T> T getBean(ApplicationContext applicationContext,String name) throws BeansException {
		return (T)applicationContext.getBean(name);
	}
	
	@SuppressWarnings ("unchecked")
    public static <T> T getSourceBean(ApplicationContext applicationContext,String name) {
		Advised advised = (Advised) applicationContext.getBean(name);
		SingletonTargetSource singTarget = (SingletonTargetSource) advised.getTargetSource();
		return (T)singTarget.getTarget();
	}
	
}
