package com.hzdracom.manager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;





import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * @Title: DateUtil.java
 * @Package com.hzdracom.util
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014-9-17 上午10:26:39
 */
public class DateUtil {
	
	
	
	private static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
	
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
	public static Date getDate(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}



	/**
	 * 获取周开始时间，默认周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date startWeek(Date date) {
		DateTime time = toDateTime(date);
		DateTime ws = time.dayOfWeek().withMinimumValue();
		return ws.toDate();
	}

	/**
	 * 获取周结束时间，默认周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date endWeek(Date date) {
		DateTime time = new DateTime(date);
		DateTime ws = time.dayOfWeek().withMaximumValue();
		return ws.toDate();
	}

	/**
	 * 获取周开始时间，默认周一
	 * 
	 * @param date
	 * @return
	 */
	public static DateTime startWeek(DateTime date) {
		DateTime ws = date.dayOfWeek().withMinimumValue();
		return ws;
	}

	/**
	 * 获取周结束时间，默认周日
	 * 
	 * @param date
	 * @return
	 */
	public static DateTime endWeek(DateTime date) {
		DateTime we = date.dayOfWeek().withMaximumValue();
		return we;
	}

	/**
	 * 是否周一
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isStartWeek() {
		DateTime now = DateTime.now();
		DateTime sw = startWeek(now);
		if (sw == now) {
			return true;
		}
		return false;
	}

	/**
	 * 获取月开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static DateTime startMonth(DateTime date) {
		DateTime ws = date.monthOfYear().withMinimumValue();
		return ws;
	}

	/**
	 * 是否是月开始时间
	 * 
	 * @return
	 */
	public static boolean isStartMonth() {
		DateTime now = DateTime.now();
		DateTime sw = startMonth(now);
		if (sw == now) {
			return true;
		}
		return false;
	}

	/**
	 * 下一周期：天
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextDay(Date date) {
		DateTime time = new DateTime(date);
		time = time.plusDays(1);
		return time.toDate();
	}

	/**
	 * 下一周期：周
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextWeek(Date date) {
		DateTime time = new DateTime(date);
		time = time.plusWeeks(1).dayOfWeek().withMinimumValue();
		return time.toDate();
	}

	/**
	 * 下一周期：月
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextMonth(Date date) {
		DateTime time = new DateTime(date);
		time = time.plusMonths(1).dayOfMonth().withMinimumValue();
		return time.toDate();
	}

	/**
	 * 上一周期开始结束时间：天
	 * 
	 * @param date
	 * @return
	 */
	public static String[] lastDay(Date date) {
		DateTime time = new DateTime(date).minusDays(1);
		String sd = time.toString(dtf);
		return new String[] { sd };
	}

	/**
	 * 上一周期开始结束时间：周
	 * 
	 * @param date
	 * @return
	 */
	public static String[] lastWeek(Date date) {
		DateTime time = new DateTime(date).minusWeeks(1);
		String sw = time.dayOfWeek().withMinimumValue().toString(dtf);
		String ew = time.dayOfWeek().withMaximumValue().toString(dtf);
		return new String[] { sw, ew };
	}

	/**
	 * 上一周期开始结束时间，月
	 * 
	 * @param date
	 * @return
	 */
	public static String[] lastMonth(Date date) {
		DateTime time = new DateTime(date).minusMonths(1);
		String sm = time.dayOfMonth().withMinimumValue().toString(dtf);
		String em = time.dayOfMonth().withMaximumValue().toString(dtf);
		return new String[] { sm, em };
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @param fmt
	 * @return
	 */
	public static String now(String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(new Date());
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String dateToString(Date date, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	public static String formatY0M0D(Date date) {
		return date == null ? "" : formatDateTime(date, "yyyyMMdd");
	}

	public static String formatYMD(Date date) {
		return date == null ? "" : formatDateTime(date, "yyyy-MM-dd");
	}

	public static Date formatYMDForDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (Util.isEmpty(date)) {
			return null;
		}
		String newDate = sdf.format(date);
		try {
			return sdf.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDateTime(Date date, String format) {
		if (date == null)
			return null;
		if (format == null)
			return date.toString();

		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/* String类型到Date类型转化 */
	public static java.util.Date StringToDate(String strDate, int type) {
		java.util.Date result = null;
		String format = "yyyy-MM-dd";
		if (type == 0) {
			format = "yyyy-MM-dd";
		}
		if (type == 1) {
			format = "yyyy/MM/dd";
		} else if (type == 2) {
			format = "yyyyMMdd";
		} else if (type == 21) {
			format = "yyyyMM";
		} else if (type == 3) {
			format = "MM/dd/yy";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 5) {
			format = "yyyyMMddHHmm";
		} else if (type == 6) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (strDate != null && !strDate.equals("")) {
			try {
				result = formatter.parse(strDate);
			} catch (ParseException ex) {
				result = null;
			}
		}
		return result;
	}

	/* Date类型转化到String类型 */
	public static String DateToString(java.util.Date date, int type) {
		String result = null;
		String format = "yyyy-MM-dd";
		if (type == 0) {
			format = "yyyy-MM-dd";
		}
		if (type == 1) {
			format = "yyyy/MM/dd";
		} else if (type == 2) {
			format = "yyyyMMdd";
		} else if (type == 21) {
			format = "yyyyMM";
		} else if (type == 3) {
			format = "MM/dd/yy";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 5) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (date != null) {
			result = formatter.format(date);
		} else {
			result = "";
		}
		return result;
	}

	public static Integer getBetweenDay(Date st, Date et) {
		if (Util.isEmpty(st) || Util.isEmpty(et)) {
			return 0;
		}
		DateTime d1 = new DateTime(st);
		DateTime d2 = new DateTime(et);
		int reuslt = Days.daysBetween(d1, d2).getDays();
		if (reuslt == 0) {
			return 1;
		}
		return 0;
	}

	public static DateTime toDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new DateTime(sdf.format(date));
	}

	public static Date plusDay(Date date, int day) {
		DateTime datetime = new DateTime(date);
		return datetime.plusDays(day).toDate();
	}

	public static Date minusDay(Date date, int day) {
		DateTime datetime = new DateTime(date);
		return datetime.minusDays(day).toDate();
	}

	public static boolean isAfterDay(Date st, Date et) {
		DateTime dateTime1 = toDateTime(st);
		DateTime dateTime2 = toDateTime(et);
		int day = Days.daysBetween(dateTime1, dateTime2).getDays();
		if (day > 0)
			return true;
		return false;
	}

	public static boolean isAfterOrEqualDay(Date st, Date et) {
		DateTime dateTime1 = toDateTime(st);
		DateTime dateTime2 = toDateTime(et);
		int day = Days.daysBetween(dateTime1, dateTime2).getDays();
		if (day > 0 || day == 0)
			return true;
		return false;
	}

	/* 把字符串的日期格式 转成yyyy-MM-dd格式 */
	public static String formatYYYYMMDD(String str, int type) {
		String sformat = "";
		if (type == 1) {
			sformat = "yyyyMMdd";
		} else if (type == 2) {
			sformat = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sf1 = new SimpleDateFormat(sformat);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfstr;
	}

	/* 把字符串的日期格式 转成yyyy-MM-dd格式 */
	public static String formatYYYYMMDDHHMMSS(String str, int type) {
		String sformat = "";
		if (type == 1) {
			sformat = "yyyyMMdd";
		} else if (type == 2) {
			sformat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sf1 = new SimpleDateFormat(sformat);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfstr;
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
		System.out.println(getDate(new Date(), 15));
	}

}
