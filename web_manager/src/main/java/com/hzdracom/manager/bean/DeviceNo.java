/**
 * 
 */
package com.hzdracom.manager.bean;

/** 
 *  Title: com.hzdracom.manager.bean
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年4月25日 
 */
public class DeviceNo implements  java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String   deviceNo;
	
	private Integer status;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
