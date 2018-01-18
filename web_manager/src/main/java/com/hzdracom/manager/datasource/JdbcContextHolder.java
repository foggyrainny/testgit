package com.hzdracom.manager.datasource;

/**
 * @Title: JdbcContextHolder.java
 * @Package com.hzdracom.dao.base
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2015-5-27 下午3:29:26
 */
public class JdbcContextHolder
{	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}
	
	public static String getCustomerType() {
		return contextHolder.get();
	}
	
	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
