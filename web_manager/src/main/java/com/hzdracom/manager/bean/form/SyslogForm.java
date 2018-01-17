package com.hzdracom.manager.bean.form;

import java.io.Serializable;

/**
 * @title: SyslogForm.java
 * @pacjage: com.hzdracom.manager.bean.form
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月10日 下午4:59:21
 */
public class SyslogForm  extends PageForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6027977238321541482L;
	
	private String account;
	private String name;
	private String startTime;
	private String endTime;
	private String opType;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	

}
