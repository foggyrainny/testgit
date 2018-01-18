/**
 * 
 */
package com.hzdracom.manager.bean;

/** 
 *  Title: com.hzdracom.manager.bean
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年3月21日 
 */
public class Selector implements java.io.Serializable{

	
	private  String  id;
	
	private String parentId;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
