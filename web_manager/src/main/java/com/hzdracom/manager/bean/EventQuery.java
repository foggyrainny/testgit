/**
*
* 项目名称：gf_manager
* 类名称：EventQuery
* 类描述：事件查询
* 创建人：JJJ
* 创建时间：2017年4月27日 上午10:53:42
* 修改人：
* 修改时间：
* 修改备注：
* @version V1.0
* 
*/

package com.hzdracom.manager.bean;

import java.io.Serializable;
import java.util.Date;

public class EventQuery  implements Serializable{
	
	private static final long serialVersionUID = 7648161620215166710L;
	
	private Long id;   					//id
	private String smuId;			//smuId
	private Long sessionId;		//SessionId
	private String devId;			//设备编号
	private String eventId;		//事件编号
	private Date reportDate;	//上报时间
	private Date createtime;	//入库时间
	private String name;			//电站名称
	private Integer officeId;		//机构ID
	private String remark;         //事件描述
	
	
	private String value;			//事件码
	
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the smuId
	 */
	public String getSmuId() {
		return smuId;
	}
	/**
	 * @param smuId the smuId to set
	 */
	public void setSmuId(String smuId) {
		this.smuId = smuId;
	}
	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the devId
	 */
	public String getDevId() {
		return devId;
	}
	/**
	 * @param devId the devId to set
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}
	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
