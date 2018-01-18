package com.hzdracom.manager.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.convert.converter.Converter;


public class DateConverter implements Converter<String, Date>  {

	private static final  Map<String, String>  formarts = new HashMap<String, String>();
	static{
		formarts.put("yyyy","^\\d{4}$");
		formarts.put("yyyy-MM","^\\d{4}-\\d{1,2}$");
		formarts.put("yyyy-MM-dd","^\\d{4}-\\d{1,2}-\\d{1,2}$");
		formarts.put("yyyy-MM-dd hh:mm","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
		formarts.put("yyyy-MM-dd hh:mm:ss","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
	}

	public Date convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		
		for (Entry<String, String> format : formarts.entrySet()) {
			if(source.matches(format.getValue())) {
				  return parseDate(source, format.getKey());
			}
		}
		throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public  Date parseDate(String dateStr, String format) {
		Date date=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = (Date) dateFormat.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}
	public static void main(String[] args) {
		System.err.println(new DateConverter().convert("2014-04"));
	}
}
