package com.hzdracom.manager.util;

public class StringUtil {
	/**
	 * 判断字符串(过滤前后空格)是否为空或null，为空返回true
	 * 
	 * @param str 待判定字符串
	 * @return 是否为空或null
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}
	/**
	 * 判断字符串是否不为空
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}

	/**
	 * 是否包含空字符串
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean hasEmptyStr(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 对象转为string格式，为空时，转为默认带入字符串
	 * 
	 * @param obj 待转对象
	 * @param defaultStr 默认字符串
	 * @return 字符串
	 */
	public static String objToString(Object obj, String defaultStr) {
		if (obj == null) {
			return defaultStr;
		} else {
			return obj.toString();
		}
	}

	/**
	 * 判断字符串长度是否合法
	 * 
	 * @param instr 输入字符串
	 * @param minLength 最小长度
	 * @param maxLength 最大长度
	 * @return true=合法
	 */
	public static boolean checkStringLength(String instr, int minLength, int maxLength) {
		return !(instr.length() < minLength || instr.length() > maxLength);
	}

	/**
	 * 判断字符串是否全是数字
	 * 
	 * @param str 输入字符串
	 * @return true=全是数字
	 */
	public static boolean isNum(String str) {
		if (null == str) {
			return false;
		}
		return str.matches("\\d+");
	}

	/**
	 * 判断是否为整数
	 * @param str 输入字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		return str.matches("^[-\\+]?[\\d]+$");
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * @param str 传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		return str.matches("^[-\\+]?\\d+\\.\\d+$");
	}

	/**
	 * 判断是否URL
	 * @param url 路径
	 * @return true=是url
	 */
	public static boolean isURL(String url) {
		if (isEmpty(url)) {
			return false;
		}
		return url.matches("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	}

	/**
	 * 判断是否Email
	 * @param email 邮箱地址
	 * @return true=是邮箱
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		return email.matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*");
	}

}
