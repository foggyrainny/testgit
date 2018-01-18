package com.hzdracom.core.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @Title: DateUtil.java
 * @Package com.hzdracom.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014-9-17 上午10:26:39
 */
public class DateUtil
{
	/**
	 * 
	* @Title: getDate 
	* @Description: 日期加减操作
	* @param @param date
	* @param @param day
	* @param @return 设定文件 
	* @return Date 返回类型 
	* @throws 
	* @date 2014-9-17 上午10:29:36
	 */
	@SuppressWarnings ("static-access")
    public static Date getDate(Date date,int field,int value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field,value);
		return calendar.getTime();
	}
	
	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2014-9-17 上午10:26:39
	 */
	
	public static void main(String[] args) {
		System.out.println(getDate(new Date(),Calendar.DATE,1));
		System.out.println(getDate(new Date(),Calendar.MINUTE,5));
	}
	
}
