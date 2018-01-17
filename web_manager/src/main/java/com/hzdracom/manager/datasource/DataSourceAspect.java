package com.hzdracom.manager.datasource;



/**
 * @Title: DataSourceAspect.java
 * @Package com.hzdracom.dao.base
 * @Description: TODO(添加描述)
 * @author 刘章
 * @param <JoinPoint>
 * @date 2015-5-27 下午4:23:26
 */
public class DataSourceAspect{
	
	public static void setDataSource(String db){
		JdbcContextHolder.setCustomerType(db);
	}
	
	public void setSysDataSource() {
		System.out.println("-----sys------");
		JdbcContextHolder.setCustomerType("sys");
	}
	
	public void setTmpDataSource(){
		System.out.println("-----tmp------");
		JdbcContextHolder.setCustomerType("tmp");
	}
	
}
