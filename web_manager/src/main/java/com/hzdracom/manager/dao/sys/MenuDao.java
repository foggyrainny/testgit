package com.hzdracom.manager.dao.sys;

import java.util.List;

import com.hzdracom.manager.bean.sys.Menu;

/**
 * @title: gf_manager
 * @pacjage: 
 * @description: TODO
 * @author: 高辉
 * @date: 2017年6月9日 下午4:10:47
 */
public interface MenuDao {
	
	List<Menu> qryMenuList(Menu menu);
	
	List<Menu> qryMenuAll();
	
	Menu qryMenu(Menu menu);
	
	int addMenu(Menu menu);
	
	int updateMenu(Menu menu);
	
	List<Menu> isMenu(Integer id);
	
	int delMenu(Integer id);
	
	List<Menu> getLeftMenu(String userId);
	
	List<Menu> getAllMenus();
	
	Menu getMenuEntity(String menuUrl);
	
	
}

