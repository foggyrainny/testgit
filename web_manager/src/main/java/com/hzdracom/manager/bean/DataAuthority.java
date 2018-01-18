/**
 * 
 */
package com.hzdracom.manager.bean;

import java.util.Arrays;

/**
 * Title: com.hzdracom.manager.bean 
 * Description: Company: 杭州龙骞科技有限公司
 * @author panke
 * @date 2017年3月27日
 */
public class DataAuthority implements java.io.Serializable{

	// 是否是超级管理员
	private boolean supperAdmin;
	//  1 2 4 是找找  officeId 做 in 操作  3 这是 create_by 字段等于 userId 字段
	// 角色谁粒度  数据粒度 1 所在部门数据  2 部门及以下数据  3 本人数据  4 明细
	private int roleLevel;
	
	//  用户ID
	private Long userId;

	// 机构ID  包含下级机构ID
	private Integer officeId[];
	
	private Integer roleOfficeId;
	
	private Integer roleId;

	
	public DataAuthority() {
		
	}

	public DataAuthority(boolean supperAdmin) {
		this.supperAdmin = supperAdmin;
	}
	
	public DataAuthority(int roleLevel,Long userId) {
		this(false);
		this.roleLevel = roleLevel;
		this.userId = userId;
	}

	public DataAuthority(int roleLevel,Long userId,Integer[] officeId,Integer roleOfficeId) {
		this(roleLevel,userId);
		this.officeId = officeId;
		this.roleOfficeId = roleOfficeId;
	}


	public Integer[] getOfficeId() {
		return officeId;
	}

	public String getOfficeIds() {
		if(officeId == null || officeId.length <= 0) {
			return "";
		}
		return Arrays.toString(officeId).replace("[", "").replace("]", "");
	}
	
	public void setOfficeId(Integer officeId[]) {
		this.officeId = officeId;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setSupperAdmin(boolean supperAdmin) {
		this.supperAdmin = supperAdmin;
	}
	public boolean getSupperAdmin() {
		return this.supperAdmin;
	}

	public Integer getRoleOfficeId() {
		return roleOfficeId;
	}

	public void setRoleOfficeId(Integer roleOfficeId) {
		this.roleOfficeId = roleOfficeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
