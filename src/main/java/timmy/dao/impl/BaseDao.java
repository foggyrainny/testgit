package timmy.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import timmy.util.DbUtil;

public class BaseDao<T> {

	private  Connection con=null; 
	
	private QueryRunner qr=null;
	
	//��ȡ�б�
	protected List<T> findList(String sql,Class<T> clazz) throws Exception{
		
		con =DbUtil.getConnection();
		qr=new QueryRunner();
		List<T> list=(List<T>)qr.query(con,sql, new BeanListHandler<T>(clazz));
		DbUtils.closeQuietly(con);
		return list;
		
	}
	//��ȡ��������
	protected T findObject(String sql,Class<T> clazz)throws Exception{
		
		con =DbUtil.getConnection();
		qr=new QueryRunner();
		T t=(T)qr.query(con,sql, new BeanHandler<T>(clazz));
		
		DbUtils.closeQuietly(con);
		return t;
	}
	//��ɾ��
	protected int update(String sql)throws Exception{
		
		con =DbUtil.getConnection();
		qr=new QueryRunner();
		int rows=qr.update(con,sql);//con����
		
		DbUtils.closeQuietly(con);
		return rows;
	}
 }
