package com.hzdracom.manager.bean.form;

import java.io.Serializable;

/**
 * @title: UserForm.java
 * @pacjage: com.hzdracom.manager.bean.form
 * @description: TODO
 * @author: 高辉
 * @date: 2017年4月7日 下午4:17:31
 */
public class UserForm extends PageForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6366215285072932992L;
	
	
	private String officeId;
	private String loginName;
	private String userName;
	
	
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
