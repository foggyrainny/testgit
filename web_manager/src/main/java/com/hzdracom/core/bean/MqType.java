package com.hzdracom.core.bean;

/**
 * @Title: MqType.java
 * @Package com.hzdracom.core.bean
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2017年3月8日 下午9:16:11
 */
public enum MqType {
	
	SMU_UPLOAD("SMU上报信息", "SMU_UPLOAD"),
	TCP_PUSH("通信服务器下发","TCP_PUSH"),
	MANAGER_MSG_TOPIC("管理后台发送信息","MANAGER_MSG_TOPIC"),
	MANAGER_MSG_FANOUT("管理后台接收信息","MANAGER_MSG_FANOUT"),
	NOTICE_FANOUT("管理后台接收通知","NOTICE_FANOUT"),
	
	CACHE_EVENT("缓存事件发送","CACHE_EVENT"),
//	CACHE_EVENT_REVICE("缓存时间接口","CACHE_EVENT_REVICE"),
	
	//单独调试升级的数据处理时使用
	//SMU_UPLOAD("SMU上报信息", "SMU_UPLOAD_TEST"),
	//TCP_PUSH("通信服务器下发","TCP_PUSH_TEST"),
	;
	
	private String name;
	private String value;
	
	MqType(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
}
