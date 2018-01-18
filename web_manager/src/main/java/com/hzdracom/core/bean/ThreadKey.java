 package com.hzdracom.core.bean; 
/** 
 * @Title: ThreadKey.java
 * @Package com.wasu.pub.thread.base 
 * @Description: TODO(添加描述) 
 * @author 刘章 
 * @date 2016年3月8日 上午10:36:51 
 */
public enum ThreadKey {
	
	test("test"),smuLog("smuLog"),smuRouteIp("smuRouteIp");

	private String value;
	
	ThreadKey(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

