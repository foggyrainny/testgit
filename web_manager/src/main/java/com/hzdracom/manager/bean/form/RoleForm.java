package com.hzdracom.manager.bean.form;

import java.io.Serializable;

/**
 * @title: RoleForm.java
 * @pacjage: com.hzdracom.manager.bean.form
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月30日 上午9:48:24
 */
public class RoleForm extends PageForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8222837721211804187L;
	
	
	private Integer officeId;
	
	private String roleName;

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
