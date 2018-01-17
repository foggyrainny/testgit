package com.hzdracom.manager.service.sys;

import java.util.List;

import com.hzdracom.manager.bean.sys.Menu;


public interface IMenuService {
	

	/**
	 * 获取菜单列表
	 * @description: 
	 * @date: 2017年3月15日 上午11:29:50
	 */
	public List<Menu> qryMenuList(Menu menu) throws Exception;//
	
	
	
	public List<Menu> qryMenuAll() throws Exception;
	
	
	/**
	 * 获取菜单列表
	 * @description: 
	 * @date: 2017年3月15日 上午11:29:50
	 */
	public Menu qryMenu(Menu menu) throws Exception;//
	
	/**
	 * 
	 * @description: 
	 * @date: 2017年3月16日 下午4:08:07
	 */
	public boolean addMenu(Menu menu) throws Exception;//
	
	/**
	 * 
	 * @description: 
	 * @date: 2017年3月16日 下午4:08:07
	 */
	public boolean updateMenu(Menu menu) throws Exception;//
	
	
	
	/**
	 * 获取用户左侧菜单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getLeftMenu(int userId,int roleId) throws Exception;//

	/**
	 * 删除单个菜单
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	public boolean delMenu(int menuId) throws Exception;//
	
	public Menu getMenuEntity(String menuUrl) throws Exception;
}
