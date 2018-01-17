/**
 * 
 */
package com.hzdracom.manager.bean;

/** 
 *  Title: com.hzdracom.manager.annotation
 *  Description: 
 *  Company: 杭州龙骞科技有限公司 
 *  @author  panke
 *  @date 2017年6月29日 
 */
public enum Option {

	LOGIN(0),     // 登录
//	EXIT("退出"),
	PAGE(1),      //  单纯的进入页面， 不查询数据时
	QUERY(2),     // 查询
	DEL(3),       //  刪除
	ADD(4),       // 添加
	MODIFY(5),  // 菜单权限更新 等 也算修改
	COMMAND(6); // 指令发送
	
	private int value;
	
	Option(int value){
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
