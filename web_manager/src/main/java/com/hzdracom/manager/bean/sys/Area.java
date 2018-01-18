/**
 * 
 */
package com.hzdracom.manager.bean.sys;

/**
 * Title: com.hzdracom.manager.bean.sys Description: Company: 杭州龙骞科技有限公司
 * 
 * @author panke
 * @date 2017年3月23日
 */
public class Area implements  java.io.Serializable{

	/* CREATE TABLE `sys_area_id;// ( */
	private String areaId;// varchar(255) NOT NULL DEFAULT '',
	private String nameEn;// varchar(255) DEFAULT NULL,
	private String nameCn;// varchar(255) DEFAULT NULL,
	private String districtEn;// varchar(255) DEFAULT NULL,
	private String districtCn;// varchar(255) DEFAULT NULL,
	private String provEn;// varchar(255) DEFAULT NULL,
	private String provCn;// varchar(255) DEFAULT NULL,
	private String nationEn;// varchar(255) DEFAULT NULL,
	private String nationCn;// varchar(255) DEFAULT NULL,
	private String latitude;// double DEFAULT NULL,
	private String longitude;// double DEFAULT NULL,
	
	
	private String id;
	private String name;
	private String parentId;
	private Integer hasChild;

	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getDistrictEn() {
		return districtEn;
	}

	public void setDistrictEn(String districtEn) {
		this.districtEn = districtEn;
	}

	public String getDistrictCn() {
		return districtCn;
	}

	public void setDistrictCn(String districtCn) {
		this.districtCn = districtCn;
	}

	public String getProvEn() {
		return provEn;
	}

	public void setProvEn(String provEn) {
		this.provEn = provEn;
	}

	public String getProvCn() {
		return provCn;
	}

	public void setProvCn(String provCn) {
		this.provCn = provCn;
	}

	public String getNationEn() {
		return nationEn;
	}

	public void setNationEn(String nationEn) {
		this.nationEn = nationEn;
	}

	public String getNationCn() {
		return nationCn;
	}

	public void setNationCn(String nationCn) {
		this.nationCn = nationCn;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

}
