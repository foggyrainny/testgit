package com.hzdracom.manager.bean.sys;

import java.io.Serializable;

/**
 * @title: Office.java
 * @pacjage: com.hzdracom.manager.bean.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月22日 下午4:01:54
 */
public class Office implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 248895785069414149L;

	
	private Integer id;
	private String name;
	private String code;
	private Integer areaId;
	private String areaName;
	private Integer type;
	private Integer level;
	private Integer parentId;
	private String parentIds;
	private String address;
	private String zipCode;
	private String master;
	private String phone;
	private String fax;
	private String email;
	private String url;
	private String logoUrl;
	private String xcUrl;
	private String backgroundUrl;
	private String remark;
	private Integer isDelete;
	
	private Integer hasChild;
	private String districtEn;
	private String provEn;
	
	
	
	public String getDistrictEn() {
		return districtEn;
	}
	public void setDistrictEn(String districtEn) {
		this.districtEn = districtEn;
	}
	public String getProvEn() {
		return provEn;
	}
	public void setProvEn(String provEn) {
		this.provEn = provEn;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getHasChild() {
		return hasChild;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getXcUrl() {
		return xcUrl;
	}
	public void setXcUrl(String xcUrl) {
		this.xcUrl = xcUrl;
	}
	public String getBackgroundUrl() {
		return backgroundUrl;
	}
	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
