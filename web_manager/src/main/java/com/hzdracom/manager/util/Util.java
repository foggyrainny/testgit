package com.hzdracom.manager.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class Util
{
	
	private final static String imgViewUrl = Configuration.readConfigString("imgViewUrl","config");  //图片查看地址
    private final static String imgThumbnailUrl = Configuration.readConfigString("imgThumbnailUrl","config");  //图片缩略图地址
	
    private static Logger  log = LoggerFactory.getLogger(Util.class);
    
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
		return (string == null || "".equalsIgnoreCase(string.trim()) || string.equals("undefined"));
	}
	
	/**
	 * 判断object为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null || obj=="" || obj=="[]");
	}
	
	/**
	 * 判断list<T>为空
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmpty(List<T> list){
		return (list==null || list.size()==0);
	}
	
	/**
	 * 参数是否是有效的数字，同时满足不为空、大于0两个条件
	 * @param num  参数
	 * @return
	 */
	public static boolean isEffectiveNumber(Integer num){
		return (isNotEmpty(num) && (num>0));
	}
	
	/**
	 * 参数是否是有效的数字，同时满足不为空、大于0两个条件
	 * @param num  参数
	 * @return
	 */
	public static boolean isEffectiveNumber(Long num){
		return (isNotEmpty(num) && (num>0));
	}
	
	/**
	 * 参数是否是有效的数字，同时满足不为空、大于0两个条件
	 * @param num  参数
	 * @return
	 */
	public static boolean isEffectiveNumber(Double num){
		return (isNotEmpty(num) && (num>0));
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
	 * 判断list不为空
	 * @param list
	 * @return
	 */
	public static <T> boolean isNotEmpty(List<T> list){
		return (list!=null && list.size()>0);
	}
	
	/**
	 * 判断Object不为空
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object){
		return (object!=null && object!="" && object!="[]");
	}
	
	/**
	 * 判断Set不为空
	 * @param <T>
	 * @paramobject
	 * @return
	 */
	public static <T> boolean isNotEmpty(Set<T> set){
		return (set!=null && set.size()>0);
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
	
	public  static String getOrderId(String prefix){
		return prefix + DateUtil.DateToString(new Date(), 5) + makeRandomForNum(3);
	}
	/**
	 * 图片名称改为UUID，默认类型JPG
	 * @param imgName
	 * @return
	 */
	public static String getUUIDImgName(String imgName){
		String imgType = ".jpg";
		String[] imgNames = imgName.split("\\.");
		if(imgNames.length==2){
			imgType="."+imgNames[1];
		}
		return UUID.randomUUID().toString()+imgType;
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
		else if (type == 5)
		{
			format = "MM月dd日 HH:mm:ss";
		}
		else if (type == 6)
		{
			format = "yyyyMMddHHmmss";
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
			format = "yyyy-MM-dd HH:mm";
		}
		else if (type == 5)
		{
			format = "yyyy-MM-dd HH:mm:ss";
		}
		else if (type == 7)
		{
			format = "yyyyMMddHHmmss";
		}
		else if (type == 6)
		{
			format = "yyyyMMddHHmm";
		}
		else if (type == 8)
		{
			format = "yyyy-MM";
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
	
	//比较是否在该时间段
	public static boolean IsTimeIn(Date begin, Date end) {
		Date time = new Date();
		return time.getTime() >= begin.getTime() && time.getTime() <= end.getTime();
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
	
	public static int VersionUpdate(String old_version, String new_version) {
		String[] olds = old_version.split("\\.");
		String[] news = new_version.split("\\.");
		if (olds.length == news.length)
		{
			if (Integer.parseInt(news[0]) > Integer.parseInt(olds[0]))
			{
				return 1;
			}
			else if (Integer.parseInt(news[0]) == Integer.parseInt(olds[0]))
			{
				if (Integer.parseInt(news[1]) > Integer.parseInt(olds[1]))
				{
					return 1;
				}
				else if (Integer.parseInt(news[1]) == Integer.parseInt(olds[1]))
				{
					if (Integer.parseInt(news[2]) > Integer.parseInt(olds[2])) { return 2; }
				}
			}
		}
		return 0;
	}
	
	public static void printSQL(String sql) {
		//System.out.println("[SQL]:"+sql);
	}
	
	public static String listToSeparateString(List list, String separate){
		if(list==null || list.size()==0){
			return "";
		}
		StringBuffer result = new StringBuffer();
		result.append(list.get(0));
		for(int i=1; i<list.size(); i++){
			result.append(separate);
			result.append(list.get(i) );
		}
		return result.toString();
	}
	
	/**
	 * 将中文括号（）转换为英文括号()
	 * @param str
	 * @return
	 */
	public static String switchParentheses(String str){
		if(isNotEmpty(str)){
			str = str.replaceAll("（", "(");
			str = str.replaceAll("）", ")");
		}
		return str;
	}
	
	/**
	 * 将中文逗号，转换为英文逗号,
	 * @param str
	 * @return
	 */
	public static String switchComma(String str){
		if(isNotEmpty(str)){
			str = str.replaceAll("，", ",");
		}
		return str;
	}
	
	/**
	 * 替换团购富文本信息中的图片地址，从图片服务器获取500×500大小的图片
	 * @type 0无后缀,1后缀为"(例如：jpg")
	 * @param content
	 * @return
	 */
	public static String replaceImgUrl(String content, int width, int height, int suffixType){
		String[] imgType = {"gif","jpg","jpeg","png","bmp","GIF","JPG","JPEG","PNG","BMP"};
		String imgSuffix = "?c=1&w="+width+"&h="+height+"&p=0";
		String suffix = "";
		if(suffixType==1){
			suffix = "\"";
		}
		
		content = content.replaceAll(imgViewUrl, imgThumbnailUrl);
		for(String type : imgType){
			String temp = "."+type;
			type = "."+type+suffix;
			content = content.replaceAll(type, temp+imgSuffix+suffix);
		}
		return content;
	}
	
	public static boolean isViewImg(String str){
		return str.indexOf(imgViewUrl)>0 ? true : false;
	}
	
	/**
	 * 转换GET请求参数的中文编码
	 * @param params
	 * @return
	 */
	public static String coverGetParamCharset(String params){
		String result = "";
		try {
			result = new String(params.trim().getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return result;
	}

	/**
	 * 合计指定的属性
	 * @param list
	 * @param property
	 * @param _class
	 * @return
	 * @throws Exception
	 */
	public static Object commonSum(List<?> list, String[] property, Class<?> _class) throws Exception {
		Object detail = Class.forName(_class.getName()).newInstance();
		DecimalFormat df = new DecimalFormat("#.##");
		for(String column : property) {
			Double d = 0.0;
			Object o = null;
			for(Object object : list){
				Object obj = ReflectionUtils.invokeGetterMethod(object, column);
				if(Util.isNotEmpty(obj)){
					d += Double.valueOf(obj.toString());
				}
			}
			o = d;
			String fileType =  ReflectionUtils.getFieldType(detail, column).toString();
			if(fileType.equals("class java.lang.Integer") || fileType.equals("int")){
				o = d.intValue();
			}else{
				String temp = df.format(o);
				if(Util.isEmpty(temp)){
					o = 0.0;
				}else{
					o = Double.parseDouble(temp);
				}

			}
			ReflectionUtils.setFieldValue(detail, column, o);
		}
		return detail;
	}
	
	/**
	 * 是否是可用的查询参数，同时满足不为空、大于0两个条件
	 * @param num  参数
	 * @return
	 */
	public static boolean isAvailable(Integer num){
		return (isNotEmpty(num) && (num>0));
	}
	

	/**
	 * 是否是可用的查询参数，同时满足不为空、大于0两个条件
	 * @param num  参数
	 * @return
	 */
	public static boolean isAvailable(Double num){
		return (isNotEmpty(num) && (num>0));
	}
	
	/**
	 * 
	* @Title: File2byte 
	* @Description: TODO(文件转成byte数组) 
	* @param @param file11
	* @param @return 设定文件 
	* @return byte[] 返回类型 
	* @throws 
	* @date 2016-4-23 下午1:43:45
	 */
	public static byte[] File2byte(File file11) {
		byte[] buffer = null;
		try
		{
			File file = file11;
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return buffer;
	}
	
	/**float小数两位以上保留两位小数*/
	public static Float roundDouble(float val, int precision) {
		Float ret = null;
		try
		{
			BigDecimal b = new BigDecimal(val);
			ret = b.setScale(precision, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	

	   public static String getIpAddress(HttpServletRequest request) {  
	        String ip = request.getHeader("x-forwarded-for");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;  
	    }  
	
	
	   
	   /**
	     * 获取当前Request对象.
	     *
	     * @return 当前Request对象 可能为null
	     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
	     */
	    public static HttpServletRequest currentRequest() throws IllegalStateException {
	        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        if (attrs == null) {
	        	log.info("当前线程中不存在 Request 上下文");
	        }
	        return attrs.getRequest();
	    }
	    
	    /**
	     * 判断字符是否为IP地址（无法判断带端口ＩＰ）
	     * 
	     * @param IP
	     * @return
	     */
	    public static boolean isIPCheck(String IP) {
	        if (IP != null && !IP.isEmpty()) {
	            // 定义正则表达式
	            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
	                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
	                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
	                      + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
	            // 判断ip地址是否与正则表达式匹配
	            if (IP.matches(regex)) {
	                // 返回判断信息
	                return true;
	            } else {
	                // 返回判断信息
	                return false;
	            }
	        }
	        return false;
	    }
	    
	    /**
	     *  根据电站名  计算出用于排序的电站名称
	     *  
	     *  XXX#00000[光伏电站]
	     *  XXX 为电站真实名
	     *  #000000  为 电站序号
	     *  [光伏电站] 
	     *  
	     */
	    public static String  getSortStationName(String name){
	    	
	    	if(name == null || "".equals(name)) {
	    		return name;
	    	}
	    	name = name.trim();
	    	
	    	//  1 处理 存在 #和数字的  将数字都格式化成 6位
			String newName = "";
			// 处理 XXXX#01XXXX 这种情况
			Pattern p = Pattern.compile("#[0-9]{1,6}");
	    	Matcher match = p.matcher(name);
	    	if(match.find()){
	    		String temp = match.group();
	    		String str = String.format("%06d", Integer.parseInt(temp.replace("#", "")));  
	    		newName =  name.replaceFirst(temp, "#"+str);
	    		return newName;
	    	}
	    	
	    	p = Pattern.compile("光伏电站$");
	    	match = p.matcher(name);
	    	
	    	if(match.find()) {
	    		String temp = match.group();
	    		newName = name.replaceFirst(temp, "#" + String.format("%06d", 0) + temp);
	    		return newName;
	    	}
	    	
	    	newName =  name +  "#"+String.format("%06d", 0);
	    	
	    	return newName;
	    }
	
	public static void main(String[] age) {
//		String content = "http://122.229.30.34:6081/group1/M00/F8/49/F_g07LHYJVPbI0bU58.JPG";
////		String temp = replaceImgUrl(content, 300, 300);
////		System.out.println(temp);
//		

		//System.out.println(roundDouble(2.5f,2));
		
		
    	System.out.println(getSortStationName("xxxx"));
    	System.out.println(getSortStationName("xxxx#123"));
    	System.out.println(getSortStationName("xxxx#023"));
    	System.out.println(getSortStationName("xxxx#023"));
    	System.out.println(getSortStationName("xxxx光伏电站"));
	}
	
}
