package com.hzdracom.manager.bean.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Role implements Serializable {
	private static final long serialVersionUID = -633644681799048971L;
	private Integer id;// 角色编号
	private String roleName;// 角色名
	private Integer status;// 状态 1=正常 2=删除 3=禁用
	private Date createTime;// 创建时间
	private String remark;// 备注
	private Long epId;// 归属帐号
	private Integer officeId;//
	private Integer roleLevel;
	
	private String officeName;
	
	private String menus;
	private String offices;
	private List<String> menuList;
	private List<Map<String, String>> officeList;
	
	private String createBy;
	private String updateBy;
	
	private String name;
	private int parentId;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getEpId() {
		return epId;
	}

	public void setEpId(Long epId) {
		this.epId = epId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getOffices() {
		return offices;
	}

	public void setOffices(String offices) {
		this.offices = offices;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public List<Map<String, String>> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Map<String, String>> officeList) {
		this.officeList = officeList;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
