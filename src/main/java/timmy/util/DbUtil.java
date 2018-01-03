package timmy.util;

import java.sql.Connection;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtil {
	
	private static Connection getConnection(int type) throws Exception{
		if(type == 1){//c3p0ÊôÐÔÎÄ¼þ
			ComboPooledDataSource cpd = new ComboPooledDataSource();
			return cpd.getConnection();
		}else if(type == 2){//jndi + c3p0
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/mysql");
			return ds.getConnection();	
		}
		return null;
	}
	
	public static Connection getConnection() throws Exception{
		return getConnection(2);
	}

}
