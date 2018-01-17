/**
 * 
 */
package com.hzdracom.manager.bean.sys;

import java.util.Date;

/** 
 *  Title: com.hzdracom.manager.bean.sys
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月25日 
 */
public class Image implements java.io.Serializable{

	private Long id;
	private String imageUrl;
	private String smallUrl;
	private String middleUrl;
	private String largeUrl;
	private String type;
	private String subType;
	private String relationId;
	private Date  createTime;
	private Date  modifyTime;
	private int  delFlag;
	
	public Image() {
	}
	public Image(String imageUrl,String type,String relationId) {
		this.imageUrl = imageUrl;
		this.type = type;
		this.relationId = relationId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSmallUrl() {
		return smallUrl;
	}
	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}
	public String getMiddleUrl() {
		return middleUrl;
	}
	public void setMiddleUrl(String middleUrl) {
		this.middleUrl = middleUrl;
	}
	public String getLargeUrl() {
		return largeUrl;
	}
	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
}
