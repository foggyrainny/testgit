package com.hzdracom.manager.bean.sys;

import java.util.Date;

/**
 * @类功能说明：系统日志实体类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：杭州龙骞科技有限公司
 * @作者：
 * @创建时间：
 * @版本：V1.0
 */
public class SysLog implements  java.io.Serializable {
	
	private Long  id;
	private String account;
	private String name;
	private String content;
	private Date  operationtime;
	
	private Integer opType;
	
	private String ip;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getOperationtime() {
		return operationtime;
	}
	public void setOperationtime(Date operationtime) {
		this.operationtime = operationtime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getOpType() {
		return opType;
	}
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
}
