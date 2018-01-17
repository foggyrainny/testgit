/**
 * =================================================
 *
 * @copyright 杭州龙骞科技有限公司 2012-1014
 * =================================================
 */
package com.hzdracom.manager.util;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读写工具类
 * 
 * @author qfq
 * @date 2012-10-16 15:44:34
 * @version 1.0
 */
public class Configuration {

	private static final Logger log = LoggerFactory.getLogger(Configuration.class);

	/**
	 * 读取配置文件信息
	 * 
	 * @param name 读取节点名
	 * @param fileName 文件名
	 * @return 读取的节点值
	 */
	public static String readConfigString(String name, String fileName) {
		String result = "";
		try {
			ResourceBundle rb = ResourceBundle.getBundle(fileName);
			result = rb.getString(name);
		} catch (Exception e) {
			log.error("从" + fileName + "读取" + name + "出错:" + e.getMessage());
		}
		return result;
	}
	
	public static void main(String[] age){
		System.out.println(readConfigString("htmlLocalPath","config"));
	}
}
