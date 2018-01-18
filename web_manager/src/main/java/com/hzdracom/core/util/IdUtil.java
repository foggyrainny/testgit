 package com.hzdracom.core.util; 

import java.util.UUID;

/** 
 * @Title: IdUtil.java
 * @Package com.hzdracom.core.util 
 * @Description: TODO(添加描述) 
 * @author 刘章 
 * @date 2017年5月2日 上午10:03:19 
 */
public class IdUtil
{
	/**
	 * 
	* @Title: id 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @date 2017年5月2日 上午10:04:07
	 */
	public static String id(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}

