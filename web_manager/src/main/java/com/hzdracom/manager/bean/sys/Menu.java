package com.hzdracom.manager.bean.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = -6555413408034785178L;
	private long Id;// 编号
	private String menuName;// 菜单名
	private String menuNameEn;// 菜单名
	private Integer parentId;// 上级编号
	private String parentName;// 上级菜单名称
	private String menuUrl;// 菜单链接地址
	private Integer sortNum;// 排序
	private String isHtmlElement;// 是否同时是页面元素
	private String htmlCode;// 页面元素编码--备用
	private String remark;// 备注
	private Date createTime;// 创建时间
	private Date modifyTime;// 最近更新时间
	private List<Menu> childMenuList;// 子级菜单列表
	private String isShow;// 是否显示
	private Integer appId;// 应用编号
	
	private Integer childs;//子菜单长度
	
	private String name;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<Menu> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<Menu> childMenuList) {
		this.childMenuList = childMenuList;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsHtmlElement() {
		return isHtmlElement;
	}

	public void setIsHtmlElement(String isHtmlElement) {
		this.isHtmlElement = isHtmlElement;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Integer getChilds() {
		return childs;
	}

	public void setChilds(Integer childs) {
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuNameEn() {
		return menuNameEn;
	}

	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}


}
