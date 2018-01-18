package com.hzdracom.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import sun.net.util.IPAddressUtil;


public class Util
{
	private static final Logger log = Logger.getLogger(Util.class);
	
	/**
 	 * 
 	* @Title: makeRandom 
 	* @Description: TODO(这里用一句话描述这个方法的作用) 
 	* @param @param s
 	* @param @param e
 	* @param @return 设定文件 
 	* @return int 返回类型 
 	* @throws 
 	* @date 2016年11月17日 下午1:42:47
 	 */
 	public static int makeRandom(int MIN,int MAX){
 		return new java.util.Random().nextInt(MAX - MIN) + MIN;
 	}
	
	/**
	 * @Title: isPortStart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param host
	 * @param @param port
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2015年7月23日 下午1:55:51
	 */
	public static boolean isPortStart(String host, int port) {
		Socket socket = null;
		try
		{
			InetAddress theAddress = InetAddress.getByName(host);
			socket = new Socket(theAddress, port);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
		finally
		{
			try
			{
				if (socket != null)
				{
					socket.close();
					socket = null;
				}
			}
			catch (Exception e)
			{
				
			}
		}
	}
	
	/**
	 * @Title: isJson
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param value
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2017年2月10日 下午6:50:13
	 */
	public static boolean isJson(String value) {
		try
		{
			new org.json.JSONObject(value);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * @Title: getContentBytes
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2016年7月14日 上午10:49:15
	 */
	public static String getContentBytes(HttpServletRequest request) throws Exception {
		int len = request.getContentLength();
		InputStream is = request.getInputStream();
		try
		{
			if (len == -1) throw new IOException("read content length is -1!");
			byte[] buffer = new byte[len];
			if (is != null) IOUtils.readFully(is, buffer);
			return new String(buffer, "UTF-8");
		}
		catch (Exception ex)
		{
			log.error(ex);
			throw ex;
		}
		finally
		{
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * @Title: readConfig
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param source
	 * @param @param keyname
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2016年5月24日 上午9:54:56
	 */
	public static String readConfig(String source, String keyname) {
		try
		{
			return ResourceBundle.getBundle(source).getString(keyname);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @Title: getHostNameForLiunx
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2016年5月24日 上午9:54:52
	 */
	public static String getHostNameForLiunx() {
		try
		{
			return (InetAddress.getLocalHost()).getHostName();
		}
		catch (UnknownHostException uhe)
		{
			String host = uhe.getMessage(); // host = "hostname: hostname"  
			if (host != null)
			{
				int colon = host.indexOf(':');
				if (colon > 0) { return host.substring(0, colon); }
			}
			return "UnknownHost";
		}
	}
	
	public static final String nei_NET = "nei";
	public static final String wai_NET = "wai";
	
	public static Map<String, List<String>> getHostIpForLiunx() {
		String def = "127.0.0.1";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try
		{
			List<String> nei = new ArrayList<String>();
			List<String> wai = new ArrayList<String>();
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements())
			{
				NetworkInterface ni = interfaces.nextElement();
				Enumeration<InetAddress> addresss = ni.getInetAddresses();
				while (addresss.hasMoreElements())
				{
					String hostAddress = addresss.nextElement().getHostAddress();
					System.out.println(hostAddress);
					if (isboolIp(hostAddress))
					{
						if (internalIp(IPAddressUtil.textToNumericFormatV4(hostAddress)))
						{
							//内网
							nei.add(hostAddress);
						}
						else
						{
							//外网
							if (!hostAddress.equals(def)) wai.add(hostAddress);
						}
					}
				}
			}
			if (nei.isEmpty()) nei.add(def);
			map.put(nei_NET, nei);
			map.put(wai_NET, wai);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Title: internalIp
	 * @Description: 是内网ip
	 * @param @param ip
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2017年3月13日 下午1:50:33
	 */
	public static boolean internalIp(String ip) {
		if (!isboolIp(ip)) return false;
		return internalIp(IPAddressUtil.textToNumericFormatV4(ip));
	}
	
	/**
	 * 判断是否为合法IP格式
	 * 
	 * @return the ip
	 */
	public static boolean isboolIp(String ipAddress) {
		String ip = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
	
	public static boolean internalIp(byte[] addr) {
		final byte b0 = addr[0];
		final byte b1 = addr[1];
		//A类 10.0.0.0--10.255.255.255 
		final byte SECTION_1 = 0x0A;
		//B类 172.16.0.0--172.31.255.255 
		final byte SECTION_2 = (byte) 0xAC;
		final byte SECTION_3 = (byte) 0x10;
		final byte SECTION_4 = (byte) 0x1F;
		//C类 192.168.0.0--192.168.255.255
		final byte SECTION_5 = (byte) 0xC0;
		final byte SECTION_6 = (byte) 0xA8;
		switch (b0) {
			case SECTION_1:
				return true;
			case SECTION_2:
				if (b1 >= SECTION_3 && b1 <= SECTION_4) { return true; }
			case SECTION_5:
				switch (b1) {
					case SECTION_6:
						return true;
				}
			default:
				return false;
		}
	}
	
	/**
	 * @Title: EmptyToNUll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param value
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @date 2012-11-13 上午11:40:34
	 */
	public static String EmptyToNUll(String value) {
		String result = value;
		if (isEmpty(result))
		{
			result = null;
		}
		return result;
	}
	
	/**
	 * 判断String为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return (string == null || "".equalsIgnoreCase(string.trim()));
	}
	
	public static int[] stringsToInts(String[] s) {
		int[] n = new int[s.length];
		for (int i = 0; i < s.length; i++)
		{
			n[i] = Integer.parseInt(s[i]);
		}
		return n;
	}
	
	/**
	 * @Title: isMobile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param str
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2015年11月17日 上午9:23:26
	 */
	public static boolean isMobile(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("1[0-9]{10}");
		m = p1.matcher(str);
		b = m.matches();
		return b;
	}
	
	/**
	 * @Title: isEmail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param email
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @date 2015年11月17日 上午9:23:39
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try
		{
			//String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的  
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的  
		if (str.length() > 9)
		{
			m = p1.matcher(str);
			b = m.matches();
		}
		else
		{
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
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
		if(isEmpty(str)) return false;
		for (int i = str.length(); --i >= 0;)
		{
			if (!Character.isDigit(str.charAt(i))) { return false; }
		}
		return true;
	}
	
	/**
	 * 过滤空字符串或者以及去掉二头多有空格
	 * 
	 * @param string
	 * @return
	 */
	public static String NullToString(String string) {
		if (isEmpty(string)) return "";
		return string.trim();
	}
	
	// 得到randlength位随机码
	public static String makeRandom(int randlength) {
		java.util.Random rand = new java.util.Random();
		String randStr = "ABCDEFGHJabcdef0123456789";
		// String randStr = "0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		for (int i = 0; i < randlength; i++)
		{
			int randNum = rand.nextInt(25);
			// int randNum = rand.nextInt(10);
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString();
	}
	
	// 得到randlength位随机码
	public static String makeRandomForNum(int randlength) {
		java.util.Random rand = new java.util.Random();
		String randStr = "0123456789";
		// String randStr = "0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		for (int i = 0; i < randlength; i++)
		{
			//int randNum = rand.nextInt(25);
			int randNum = rand.nextInt(10);
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString();
	}
	
	public static String getMAC() {
		String mac = null;
		try
		{
			Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
			
			InputStream is = pro.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message = br.readLine();
			
			int index = -1;
			while (message != null)
			{
				if ((index = message.indexOf("Physical Address")) > 0)
				{
					mac = message.substring(index + 36).trim();
					break;
				}
				message = br.readLine();
			}
			System.out.println(mac);
			br.close();
			pro.destroy();
		}
		catch (IOException e)
		{
			//System.out.println("Can't get mac address!");
			return null;
		}
		return mac;
	}
	
	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try
		{
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++)
			{
				str = input.readLine();
				if (str != null)
				{
					if (str.indexOf("MAC Address") > 1)
					{
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		}
		catch (IOException e)
		{
			// e.printStackTrace(System.out);
		}
		return macAddress;
	}
	
	/**
	 * @Title: getCpuSize
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @date 2013-12-17 下午2:38:17
	 */
	public static int getCpuSize() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	/* Date类型转化到String类型 */
	public static String DateToString(java.util.Date date, int type) {
		String result = null;
		String format = "yyyy-MM-dd";
		if (type == 0)
		{
			format = "yyyy-MM-dd";
		}
		if (type == 1)
		{
			format = "yyyy/MM/dd";
		}
		if (type == 11)
		{
			format = "yyyy.MM.dd";
		}
		else if (type == 2)
		{
			format = "yyyyMMdd";
		}
		else if (type == 20)
		{
			format = "yyyyMMddHH";
		}
		else if (type == 21)
		{
			format = "yyyyMM";
		}
		else if (type == 22)
		{
			format = "yyyy.MM";
		}
		else if (type == 23)
		{
			format = "yyyy";
		}
		else if (type == 24)
		{
			format = "MM.dd";
		}
		else if (type == 3)
		{
			format = "MM/dd/yy";
		}
		else if (type == 4)
		{
			format = "yyyy-MM-dd HH:mm:ss";
		}
		else if (type == 5)
		{
			format = "MM月dd日 HH:mm:ss";
		}
		else if (type == 6)
		{
			format = "yyyyMMddHHmmss";
		}
		else if (type == 61)
		{
			format = "yyyyMMddHHmmssSSS";
		}
		else if (type == 7)
		{
			format = "HH:mm:ss";
		}
		else if (type == 71)
		{
			format = "HHmmssSSS";
		}
		else if (type == 72)
		{
			format = "HH:mm";
		}
		else if (type == 73)
		{
			format = "HH";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (date != null)
		{
			result = formatter.format(date);
		}
		else
		{
			result = "";
		}
		return result;
	}
	
	/* String类型到Date类型转化 */
	public static java.util.Date StringToDate(String strDate, int type) {
		java.util.Date result = null;
		String format = "yyyy-MM-dd";
		if (type == 0)
		{
			format = "yyyy-MM-dd";
		}
		if (type == 1)
		{
			format = "yyyy/MM/dd";
		}
		else if (type == 2)
		{
			format = "yyyyMMdd";
		}
		else if (type == 21)
		{
			format = "yyyyMM";
		}
		else if (type == 3)
		{
			format = "MM/dd/yy";
		}
		else if (type == 4)
		{
			format = "yyyy-MM-dd HH:mm:ss";
		}
		else if (type == 14)
		{
			format = "yyyy-MM-dd HH:mm";
		}
		else if (type == 5)
		{
			format = "yyyyMMddHHmmss";
		}
		else if (type == 6)
		{
			format = "yyyyMMddHHmm";
		}
		else if (type == 61)
		{
			format = "yyyyMMddHHmmssSSS";
		}
		else if (type == 7)
		{
			format = "HH:mm";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (strDate != null && !strDate.equals(""))
		{
			try
			{
				result = formatter.parse(strDate);
			}
			catch (ParseException ex)
			{
				result = null;
			}
		}
		return result;
	}
	
	//比较指定时间是否大于现在时间
	public static boolean isNowTimeBig(Date date) {
		Date time = new Date();
		return time.getTime() >= date.getTime();
	}
	
	//比较指定时间是否大于现在时间
	public static boolean isNowTimeBig(Date now, Date date) {
		return now.getTime() >= date.getTime();
	}
	
	//比较是否在该时间段
	public static boolean IsTimeIn(Date now, Date begin, Date end) {
		return now.getTime() >= begin.getTime() && now.getTime() <= end.getTime();
	}
	
	public static boolean IsTimeWeekend() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return (week == 0 || week == 6) ? true : false;
	}
	
	public static int StringToInt(String value, int def) {
		if (isNotEmpty(value) && isNumeric(value)) { return Integer.parseInt(value); }
		return def;
	}
	
	public static long StringToLong(String value, long def) {
		if (isNotEmpty(value) && isNumeric(value)) { return Long.parseLong(value); }
		return def;
	}
	
	public static double StringToDouble(String value, double def) {
		if (isNotEmpty(value)) { return Double.parseDouble(value); }
		return def;
	}
	
	public static boolean isVersionUpdate(String old_version, String new_version) {
		int index = new_version.compareTo(old_version);
		System.out.println("index=" + index);
		if (index > 0) { return true; }
		return false;
	}
	
	/**
	 * @Title: setSystemTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param date 设定文件
	 * @return void 返回类型
	 * @throws
	 * @date 2014-9-26 上午11:01:20
	 */
	public static boolean setSystemTime(Date date) {
		String osName = System.getProperty("os.name");
		String cmd = "";
		try
		{
			System.out.println("-------OS:" + osName + "------");
			if (osName.matches("^(?i)Windows.*$"))
			{// Window 系统  
			 // 格式 HH:mm:ss  
				cmd = " cmd /c time " + DateToString(date, 7);
				Runtime.getRuntime().exec(cmd);
				// 格式：yyyy-MM-dd  
				cmd = " cmd /c date " + DateToString(date, 0);
				Runtime.getRuntime().exec(cmd);
			}
			else
			{// Linux 系统  
			 // 格式：yyyyMMdd  
				cmd = "  date -s " + DateToString(date, 2);
				Runtime.getRuntime().exec(cmd);
				// 格式 HH:mm:ss  
				cmd = "  date -s " + DateToString(date, 7);
				Runtime.getRuntime().exec(cmd);
			}
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}
	
	/**
	 * 获取客户端真实ip地址
	 * 
	 * @description
	 * @param request
	 * @return
	 * @author
	 * @date 2010-3-31
	 * @history
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static boolean StringTimeIsOk(String time, int type) {
		return StringToDate(time, type) != null ? true : false;
	}
	
	public static Long[] StringToLongArray(String value, String split) {
		List<Long> list = new ArrayList<Long>();
		String[] vs = NullToString(value).split(split);
		for (String v : vs)
		{
			if (isNumeric(v)) list.add(Long.parseLong(v));
		}
		return list.toArray(new Long[list.size()]);
	}
	
	public static double timeDifference(String start_s, String end_s) {
		try
		{
			String day = DateToString(new Date(), 0);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long t_m = 1000 * 60;
			long h = 60;
			long t_h = t_m * h;
			
			Date d1 = df.parse(day + " " + start_s + ":00");
			Date d2 = df.parse(day + " " + end_s + ":00");
			long diff = d2.getTime() - d1.getTime();
			long hours = diff / t_h;
			double f1 = new BigDecimal(((double) (diff % t_h) / t_m) / h).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			//System.out.println(hours + " " + f1);
			return hours + f1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public static double timeDifference(Date start, Date end) {
		try
		{
			long t_m = 1000 * 60;
			long h = 60;
			long t_h = t_m * h;
			long diff = end.getTime() - start.getTime();
			long hours = diff / t_h;
			double f1 = new BigDecimal(((double) (diff % t_h) / t_m) / h).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			//System.out.println(hours + " " + f1);
			return hours + f1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public static double orderTotal(double price, double time) {
		return new BigDecimal(price * time).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 根据日期字符串判断当月第几周
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getWeekStartAndEnd(Date date, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//第几周  
		//int week = calendar.get(Calendar.WEEK_OF_YEAR);  
		//第几天，从周日开始  
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		day = day == 0 ? 7 : day;
		//System.out.println(day);
		return DateToString(DateUtil.getDate(date,Calendar.DATE, -(day - 1)), type) + "-" + DateToString(DateUtil.getDate(date,Calendar.DATE, (7 - day)), type);
	}
	
	/**
	 * 根据日期字符串判断当月第几周
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getWeekStartAndEnd(Date date, int type, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getDate(date,Calendar.DATE, -1));
		//第几周  
		int w = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.setTime(date);
		int i = 0;
		if (w > week)
		{
			i = w - week;
		}
		//第几天，从周日开始  
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		day = day == 0 ? 7 : day;
		//System.out.println(day);
		return DateToString(DateUtil.getDate(date,Calendar.DATE, -(day - 1) - (i * 7)), type) + "-" + DateToString(DateUtil.getDate(date,Calendar.DATE, (7 - day) - (i * 7)), type);
	}
	
	public static String getMonthStartAndEnd(Date date, int month) {
		String yyyy = DateToString(date, 23);
		String MM = String.valueOf(month).length() == 1 ? "0" + month : String.valueOf(month);
		date = StringToDate(yyyy + MM, 21);
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(Calendar.YEAR, date.getYear());
		time.set(Calendar.MONTH, date.getMonth());//Calendar对象默认一月为0 
		int maxMM = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
		System.out.println(maxMM);
		return MM + ".01" + "-" + MM + "." + maxMM;
	}
	
	public static String getYYYY(String yyyyMM) {
		return yyyyMM.substring(0, 4);
	}
	
	public static String getMM(String yyyyMM) {
		return yyyyMM.substring(4);
	}
	
	public static String stringArrayToSqlString(String[] ids) {
		String result = null;
		for (String id : ids)
		{
			result = (result == null ? ("'" + id + "'") : (result + ",'" + id + "'"));
		}
		return result;
	}
	
	public static String longArrayToSqlString(long[] ids) {
		String result = null;
		for (long id : ids)
		{
			result = (result == null ? String.valueOf(id) : (result + "," + id));
		}
		return result;
	}
	
	public static void printSQL(String sql) {
		//System.out.println("[SQL]:"+sql);
	}
	
	public static void main(String[] age) throws Exception {
		/*
		 * System.out.println(stringArrayToSqlString(new String[] {
		 * "1",
		 * "2",
		 * "3"
		 * }));
		 */
		//System.out.println(getHostIpForLiunx());
		while (true)
        {
			int i = makeRandom(0,1);
			System.out.println(i);
			if(i>0) break;
        }
		
	}
	
}
