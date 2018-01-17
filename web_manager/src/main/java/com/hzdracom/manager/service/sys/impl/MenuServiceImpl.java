package com.hzdracom.manager.service.sys.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.dao.sys.MenuDao;
import com.hzdracom.manager.service.sys.IMenuService;
@Service
public class MenuServiceImpl implements IMenuService {
	
	@Resource
	private MenuDao dao;

	/**
	 * 获取用户左侧菜单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Menu> getLeftMenu(int userId,int roleId) throws Exception {
		List<Menu> menus = Collections.emptyList();
		if(roleId <=10 ) {
			 menus = dao.getAllMenus();
			
		} else {
			menus = dao.getLeftMenu(userId+"");
		}
		return menus ;//getMenuTree(menus);
	}

	@Override
	public List<Menu> qryMenuList(Menu menu) throws Exception {
		return dao.qryMenuList(menu);
	}
	

	@Override
	public boolean addMenu(Menu menu) throws Exception {
		return dao.addMenu(menu) > 0;
	}
	@Override
	public boolean updateMenu(Menu menu) throws Exception {
		return dao.updateMenu(menu) > 0;
	}
	
	/**
	 * 删除单个菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean delMenu(int menuId) throws Exception {
		List<Menu> list = dao.isMenu(menuId);
		int i = 0;
		if (list == null || list.size() == 0) {
			i = dao.delMenu(menuId);
		}
		return i>0;
	}


	@Override
	public Menu getMenuEntity(String menuUrl) throws Exception {
		// TODO Auto-generated method stub
		return dao.getMenuEntity(menuUrl);
	}

	@Override
	public Menu qryMenu(Menu menu) throws Exception {
		return dao.qryMenu(menu);
	}

	@Override
	public List<Menu> qryMenuAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.qryMenuAll();
	}

  

}
