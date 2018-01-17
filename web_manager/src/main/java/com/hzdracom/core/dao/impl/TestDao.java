package com.hzdracom.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.hzdracom.core.dao.CustomerJdbcTemplate;


/**
 * @Title: TestDao.java
 * @Package com.hzdracom.dao
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2017年4月27日 下午4:08:35
 */
public class TestDao
{
	
	/*public void demo(CustomerJdbcTemplate customerJdbcTemplate) {
		final List<SmuModel> users = new ArrayList();
		for (int i = 0; i < 10; i++)
		{
			SmuModel user = new SmuModel();
			user.setName("name" + i);
			users.add(user);
		}
		String sql = "insert into test_user(name,createtime) values(?,now())";
		List<Object> list = customerJdbcTemplate.batchInsert(sql, new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SmuModel user = users.get(i);
				ps.setString(1, user.getName());
			}
			
			@Override
			public int getBatchSize() {
				return users.size();
			}
		});
		for (Object object:list)
        {
	        System.out.println(object);
        }
	}*/
}
