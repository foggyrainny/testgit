package com.hzdracom.manager.util.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;



public class CommonExcelUtil {


	public static Map<String, List<String>> getTitleFieldMap(Document doc) {
		Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
		Element root = doc.getRootElement();
		for (Iterator<Element> iterator = root.elementIterator(); iterator
				.hasNext();) {
			Element e = iterator.next();
			String key = "";
			List<String> attrDataList = new ArrayList<String>();
			for (Iterator<Element> iter = e.elementIterator(); iter.hasNext();) {
				Element element = iter.next();
				String text = element.getText();
				String paramName = element.attributeValue("param");
				if ("fieldName".equalsIgnoreCase(paramName)
						&& com.github.pagehelper.util.StringUtil.isEmpty(text)) {
					key = text;
				}
				attrDataList.add(paramName + "#_#" + text);
			}
			dataMap.put(key, attrDataList);
		}
		return dataMap;
	}

	/**
	 * 根据表头转换后台数据 (List<Map<String, String>> ==> List<ArrayList<String>>)
	 * 
	 * @param dataList
	 * @param nameList
	 * @return
	 */
	public static List<ArrayList<String>> convertExcelData(
			List<Map<String, String>> dataList, String[] nameArray) {
		List<ArrayList<String>> newList = new ArrayList<ArrayList<String>>();
		if (dataList == null || nameArray == null || dataList.size() == 0 || nameArray.length ==0) {
			return null;
		}
		for (Map map : dataList) {
			List list = new ArrayList();
			for (String name : nameArray) {
				list.add(map.get(name));
			}
			newList.add((ArrayList<String>) list);
		}
		return newList;
	}
	
	/**
	 * 处理货币表示方式(逗号)
	 * @param numStr
	 * @return
	 */
	public static String convertNum(String numStr) {
		String result = numStr;
		if(StringUtils.isNotBlank(numStr)) {
			numStr = numStr.trim();
		}
		//正则表达式处理逗号
		String regexInt = "([0-9]{1,3}\\,)?([0-9]{3}\\,)*[0-9]{1,3}";
		String regexFlt = "([0-9]{1,3}\\,)?([0-9]{3}\\,)*[0-9]{1,3}(\\.[0-9]+)?";
		Pattern ptnInt = Pattern.compile(regexInt);
		Pattern ptnFlt = Pattern.compile(regexFlt);
		boolean bInt = ptnInt.matcher(numStr).matches();
		boolean bFlt = ptnFlt.matcher(numStr).matches();
		//bInt=false bFlt=false -->
		// a.非数字类型
		
		// b.b=true 
		if(bFlt || bInt) {
			result = numStr.replace(",", "");
		}
		
		return result;
	}
	/** 
	* 判断字符串是否是整数 
	*/ 
	public static boolean isInteger(String value) { 
		try { 
			Integer.parseInt(value); 
			return true; 
		} catch (NumberFormatException e) { 
			return false; 
		} 
	} 

	/** 
	* 判断字符串是否是浮点数 
	*/ 
	public static boolean isDouble(String value) { 
		try { 
			Double.parseDouble(value); 
			if (value.contains("."))  return true; 
			return false; 
		} catch (NumberFormatException e) { 
			return false; 
		} 
	} 

	/** 
	* 判断字符串是否是数字 
	*/ 
	public static java.io.Serializable isNumber(String value) { 
		java.io.Serializable x = value;
		if(isDouble(value)) {
			x = Double.parseDouble(value);
		} else if(isInteger(value)) {
			x = Integer.parseInt(value);
		}
	return x; 
	} 
	
}
