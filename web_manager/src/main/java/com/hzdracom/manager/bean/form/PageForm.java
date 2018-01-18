/**
 * 
 */
package com.hzdracom.manager.bean.form;

import com.hzdracom.manager.bean.DataAuthority;

/** 
 *  Title: com.hzdracom.manager.bean.form
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月23日 
 */
public class PageForm {

	private  int curr;
	private int size;
	// 数据权限相关信息
	private DataAuthority dataAuth;
	
	public int getCurr() {
		return curr;
	}
	public void setCurr(int curr) {
		this.curr = curr;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStart() {
		return (curr -1) * size;
	}
	public DataAuthority getDataAuth() {
		return dataAuth;
	}
	public void setDataAuth(DataAuthority dataAuth) {
		this.dataAuth = dataAuth;
	}
	
}
