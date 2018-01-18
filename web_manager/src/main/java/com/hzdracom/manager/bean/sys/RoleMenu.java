package com.hzdracom.manager.bean.sys;

import java.io.Serializable;

public class RoleMenu implements Serializable{
	private static final long serialVersionUID = -2814217116363732756L;
	private int menuId;// 编号
	private String menuName;// 菜单名
	private int parentId;// 上级编号
	private int roleId;// 角色编号
	private boolean isparent;//是否有子级

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean getIsparent() {
		return isparent;
	}

	public void setIsparent(boolean isparent) {
		this.isparent = isparent;
	}
}
