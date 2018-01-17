package com.hzdracom.manager.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @Title: DynamicDataSource.java
 * @Package com.hzdracom.dao.base
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2015-5-27 下午3:30:10
 */
public class DynamicDataSource
        extends
        AbstractRoutingDataSource
{
	
	@Override
	protected Object determineCurrentLookupKey() {
		return JdbcContextHolder.getCustomerType();
	}
}
