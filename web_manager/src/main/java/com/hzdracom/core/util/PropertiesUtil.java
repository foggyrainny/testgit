package com.hzdracom.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @Title: PropertiesUtil.java
 * @Package com.hzdracom.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2017年6月30日 上午10:08:32
 */
public class PropertiesUtil
{
	private static void init(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()) file.createNewFile();
	}
	
	//根据Key读取Value
	public static String getValueByKey(String filePath, String key) throws IOException {
		Properties pps = new Properties();
		InputStream in = null;
		try
		{
			init(filePath);
			in = new FileInputStream(filePath);
			pps.load(in);
			return pps.getProperty(key, null);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (in != null) in.close();
		}
	}
	
	//读取Properties的全部信息
	public static Map<String, String> getAllProperties(String filePath) throws IOException {
		Properties pps = new Properties();
		InputStream in = null;
		Map<String, String> map = null;
		try
		{
			init(filePath);
			in = new FileInputStream(filePath);
			pps.load(in);
			map = new HashMap<String, String>();
			Enumeration en = pps.propertyNames(); //得到配置文件的名字
			while (en.hasMoreElements())
			{
				String strKey = (String) en.nextElement();
				String strValue = pps.getProperty(strKey);
				map.put(strKey, strValue);
			}
			return map;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (in != null) in.close();
		}
	}
	
	//写入Properties信息
	public static boolean setValueByKey(String filePath, String pKey, String pValue) throws IOException {
		Properties pps = new Properties();
		InputStream in = null;
		OutputStream out = null;
		try
		{
			init(filePath);
			in = new FileInputStream(filePath);
			pps.load(in);
			//调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
			//强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			out = new FileOutputStream(filePath);
			pps.setProperty(pKey, pValue);
			//以适合使用 load 方法加载到 Properties 表中的格式，  
			//将此 Properties 表中的属性列表（键和元素对）写入输出流  
			pps.store(out, "Copyright (c) Hzdracom shmily480");
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (in != null) in.close();
			if (out != null) out.close();
		}
		
	}
	
	//删除Properties信息
		public static boolean delValueByKey(String filePath, String pKey) throws IOException {
			Properties pps1 = new Properties();
			Properties pps2 = new Properties();
			InputStream in = null;
			OutputStream out = null;
			try
			{
				init(filePath);
				in = new FileInputStream(filePath);
				pps1.load(in);
				Enumeration en = pps1.propertyNames(); //得到配置文件的名字
				while (en.hasMoreElements())
				{
					String strKey = (String) en.nextElement();
					String strValue = pps1.getProperty(strKey);
					if(strKey.equals(pKey)) continue;
					pps2.setProperty(strKey, strValue);
				}
				out = new FileOutputStream(filePath);
				//以适合使用 load 方法加载到 Properties 表中的格式，  
				//将此 Properties 表中的属性列表（键和元素对）写入输出流  
				pps2.store(out, "Copyright (c) Hzdracom shmily480");
				return true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				if (in != null) in.close();
				if (out != null) out.close();
			}
			
		}
	
	public static void main(String[] args) throws IOException {
		System.out.println(PropertiesUtil.setValueByKey("/data/my.properties", "name", "name-"+System.currentTimeMillis()));
		System.out.println(PropertiesUtil.setValueByKey("/data/my.properties", "studio", "studio-"+System.currentTimeMillis()));
		System.out.println(PropertiesUtil.getValueByKey("/data/my.properties", "name"));
		System.out.println(PropertiesUtil.getAllProperties("/data/my.properties"));
		System.out.println(PropertiesUtil.delValueByKey("/data/my.properties", "name"));
		System.out.println(PropertiesUtil.getAllProperties("/data/my.properties"));
		/*for (int i = 0; i < 10000; i++)
        {
			PropertiesUtil.setValueByKey("/data/data.properties",String.valueOf(i), "abc-"+i);
			System.out.println(PropertiesUtil.getValueByKey("/data/data.properties",String.valueOf(i)));
        }*/
		//System.out.println(PropertiesUtil.getAllProperties("/data/data.properties"));
	}
	
}
