/**
 * 
 */
package com.hzdracom.manager.bean;

import java.util.Date;

/** 
 *  Title: com.hzdracom.manager.bean
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年4月26日 
 */
public class DeviceStatusBean implements  java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceNo;
	private String deviceType;
	private Integer status;
	private Date createTime;
	
	private Integer size;
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	

}
