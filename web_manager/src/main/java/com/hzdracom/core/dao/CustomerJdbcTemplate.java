package com.hzdracom.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;


/**
 * @Title: CustomerJdbcTemplate.java
 * @Package com.hzdracom.core.service.dao
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2017年4月27日 下午4:04:01
 */
public class CustomerJdbcTemplate
        extends
        JdbcTemplate
{
	
	public CustomerJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 批量执行并返回主键
	 * @param @param sql
	 * @param @param pss
	 * @param @param generatedKeyHolder
	 * @param @return
	 * @param @throws DataAccessException 设定文件
	 * @return int[] 返回类型
	 * @throws
	 * @date 2017年4月27日 下午4:19:12
	 */
	@SuppressWarnings ({
            "unchecked",
            "rawtypes"
    })
    public List<Object> batchInsert(final String sql, final BatchPreparedStatementSetter pss) throws DataAccessException {
		List<Object> list = new ArrayList<Object>();
		final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		execute(new PreparedStatementCreator()
		{
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, new PreparedStatementCallback()
		{
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
				if (logger.isDebugEnabled()) logger.debug("Executing batch SQL update and returning " + "generated keys [" + sql + "]");
				try
				{
					int batchSize = pss.getBatchSize();
					int totalRowsAffected = 0;
					int[] rowsAffected = new int[batchSize];
					List generatedKeys = generatedKeyHolder.getKeyList();
					generatedKeys.clear();
					ResultSet keys = null;
					for (int i = 0; i < batchSize; i++)
					{
						pss.setValues(ps, i);
						rowsAffected[i] = ps.executeUpdate();
						totalRowsAffected += rowsAffected[i];
						try
						{
							keys = ps.getGeneratedKeys();
							if (keys != null)
							{
								RowMapper rowMapper = new ColumnMapRowMapper();
								RowMapperResultSetExtractor rse = new RowMapperResultSetExtractor(rowMapper, 1);
								generatedKeys.addAll((List) rse.extractData(keys));
							}
						}
						finally
						{
							JdbcUtils.closeResultSet(keys);
						}
					}
					if (logger.isDebugEnabled()) logger.debug("SQL batch update affected " + totalRowsAffected + " rows and returned " + generatedKeys.size() + " keys");
					return rowsAffected;
				}
				finally
				{
					if (pss instanceof ParameterDisposer) ((ParameterDisposer) pss).cleanupParameters();
				}
			}
		});
		List<Map<String, Object>> objectMap = generatedKeyHolder.getKeyList();
		for (Map<String, Object> map : objectMap)
		{
			list.add(map.get("GENERATED_KEY"));
			map.clear();
		}
		objectMap.clear();
		return list;
	}
}
