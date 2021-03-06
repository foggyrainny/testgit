package com.hzdracom.core.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	/**
	 * 判断String为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return (string == null || "".equalsIgnoreCase(string.trim()));
	}

	/**
	 * 判断String不为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return string != null && string.trim().length() > 0;
	}

	/**
	 * 判断字符串类型数字还是其他的
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 验证是否是手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String NUM = "+86";
		boolean flag = false;
		if (isEmpty(str))
		{
			return flag;
		}
		else
		{
			if (str.indexOf(NUM) > -1)
			{
				str = str.substring(NUM.length(), str.length());
			}
			if (str.charAt(0) == '0')
			{
				str = str.substring(1, str.length());
			}
			String rex = "^1[3,5,8]\\d{9}$";
			// String rex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
			str = removeBlanks(str);
			if (str.matches(rex))
			{
				flag = true;
			}
			return flag;
		}
	}
	
	/**
	 * 是否是固话+手机号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTelephoneNumber(String str) {
		if (isEmpty(str)) { return false; }
		if (!str.matches("^\\d{3,}$")) { return false; }
		if (str.matches("^1[358]\\d*")) { return str.length() == 11; }
		return str.charAt(0) == '0' && str.length() >= 10 && str.length() <= 12;
	}
	
	/**
	 * 判断邮箱格式
	 */
	public static boolean checkIsEmail(String str) {
		String check = "\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		if (matcher.matches())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 删除字符串中的空白符
	 * 
	 * @param content
	 * @return String
	 */
	public static String removeBlanks(String content) {
		if (content == null) { return null; }
		StringBuffer buff = new StringBuffer();
		buff.append(content);
		for (int i = buff.length() - 1; i >= 0; i--)
		{
			if (' ' == buff.charAt(i) || ('\n' == buff.charAt(i)) || ('\t' == buff.charAt(i)) || ('\r' == buff.charAt(i)))
			{
				buff.deleteCharAt(i);
			}
		}
		return buff.toString();
	}
	
	/**
	 * 判断网站格式
	 */
	public static boolean checkIsWeb(String str) {
		String check = "\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		if (matcher.matches())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 校验某个对象实例的一组数据字段是否不为空
	 * 
	 * @param rst
	 * @param obj
	 * @param fieldNames
	 * @throws Exception
	 */
	public static void validateNotEmpty(ResultValue rst, Object obj, String[] fieldNames) {
		StringBuffer sb = new StringBuffer("");
		for (String name : fieldNames) {
			Field f;
			try {
				f = obj.getClass().getDeclaredField(name);
				f.setAccessible(true);
				Object value = f.get(obj);
				if (value == null) {
					sb.append(name + ",");
					continue;
				}

				if (f.getType().equals(String.class)) {
					String v = (String) value;
					if (v.length() <= 0)
						sb.append(name + ",");
				}
			} catch (Exception e) {
				rst.setCode(ResultCode.RC_1001);
				rst.setResult(e);
				return;
			}
		}

		String s = sb.toString();
		if (s.length() > 0) {
			rst.setCode(ResultCode.RC_3001);
			rst.setResult(s.substring(0, s.length() - 1));
		}
	}

	/**
	 * 根据字段长度校验表达式，返回校验结果
	 * 
	 * @param rst
	 * @param fieldName
	 * @param express
	 */
	public static void validateLength(ResultValue rst, String fieldName, boolean express) {
		if (!express) {
			if (rst.getCode() == ResultCode.RC_3004) {
				rst.setResult("," + fieldName);
			} else {
				rst.setResult(fieldName);
			}
			rst.setCode(ResultCode.RC_3004);
		}
	}
	
	/**
	 * 根据字段长度校验表达式，返回校验结果
	 * 
	 * @param rst
	 * @param fieldName
	 * @param express
	 */
	public static void validateStringLength(ResultValue rst, String fieldName, boolean express) {
		if (!express) {
			if (rst.getCode() == ResultCode.RC_3002) {
				rst.setResult("," + fieldName);
			} else {
				rst.setResult(fieldName);
			}
			rst.setCode(ResultCode.RC_3002);
		}
	}

	/**
	 * 校验String字段长度
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param maxLength
	 * @throws Exception
	 */
	public static void validateStringLength(ResultValue rst, String fieldName, String value, int maxLength) {
		validateStringLength(rst, fieldName, value.length() <= maxLength);
	}
	
	/**
	 * 
	* @Title: validateStringLengthEquals 
	* @Description: 校验String字段长度
	* @param @param rst
	* @param @param fieldName
	* @param @param value
	* @param @param Length 设定文件 
	* @return void 返回类型 
	* @throws 
	* @date 2013-11-29 上午9:11:42
	 */
	public static void validateStringLengthEquals(ResultValue rst, String fieldName, String value, int Length){
		validateStringLength(rst, fieldName, value.length() == Length);
	}

	/**
	 * 校验数字取值区间
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param min
	 * @param max
	 * @throws Exception
	 */
	public static void validateIntInterval(ResultValue rst, String fieldName, int value, int min, int max) {
		validateLength(rst, fieldName, value >= min && value <= max);
	}

	/**
	 * 校验整数最小值-int
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param min
	 */
	public static void validateIntMinValue(ResultValue rst, String fieldName, int value, int min) {
		validateLength(rst, fieldName, value >= min);
	}

	/**
	 * 校验整数最小值-long
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param min
	 */
	public static void validateLongMinValue(ResultValue rst, String fieldName, long value, int min) {
		validateLength(rst, fieldName, value >= min);
	}
	
	/**
	 * 校验整数最小值-double
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param min
	 */
	public static void validateDoubleMinValue(ResultValue rst, String fieldName, double value, double min) {
		validateLength(rst, fieldName, value > min);
	}

	/**
	 * 校验整数最大值
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param max
	 */
	public static void validateIntMaxValue(ResultValue rst, String fieldName, int value, int max) {
		validateLength(rst, fieldName, value <= max);
	}
	
	/**
	 * 校验整数最大值
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param max
	 */
	public static void validateDoubleMaxValue(ResultValue rst, String fieldName, double value, double max) {
		validateLength(rst, fieldName, value <= max);
	}

	/**
	 * 校验数组长度区间
	 * 
	 * @param rst
	 * @param fieldName
	 * @param value
	 * @param minLength
	 * @param maxLength
	 * @throws Exception
	 */
	public static void validateObjectArrayInterval(ResultValue rst, String fieldName, Object[] value, int minLength,
			int maxLength) {
		validateLength(rst, fieldName, value.length >= minLength && value.length <= maxLength);
	}

	/**
	 * 根据字段格式校验表达式，返回校验结果
	 * 
	 * @param rst
	 * @param fieldName
	 * @param f
	 * @throws Exception
	 */
	public static void validateFormat(ResultValue rst, String fieldName, boolean f) {
		if (!f) {
			if (rst.getCode() == ResultCode.RC_3003) {
				rst.setResult("," + fieldName);
			} else {
				rst.setResult(fieldName);
			}
			rst.setCode(ResultCode.RC_3003);
		}
	}

	/**
	 * 是否有效的对象数组
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean isValidObjects(Validatable[] arr) {
		if (arr == null)
			return false;

		for (Validatable validatable : arr) {
			if (!isValidObject(validatable))
				return false;
		}

		return true;
	}

	/**
	 * 是否有效对象
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isValidObject(Validatable obj) {
		if (obj == null)
			return false;
		ResultValue rst = obj.validate();
		return rst == null || rst.getCode() == ResultCode.RC_1000;
	}

	public static void main(String[] args) {

	}

}
