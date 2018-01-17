package com.hzdracom.manager.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.form.RoleForm;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.Office;
import com.hzdracom.manager.bean.sys.PageMsg;
import com.hzdracom.manager.bean.sys.Role;
import com.hzdracom.manager.bean.sys.RoleMenu;
import com.hzdracom.manager.bean.sys.TreeNode;
import com.hzdracom.manager.dao.sys.MenuDao;
import com.hzdracom.manager.dao.sys.OfficeDao;
import com.hzdracom.manager.dao.sys.RoleDao;
import com.hzdracom.manager.service.sys.IRoleService;
import com.hzdracom.manager.util.StringUtil;

@Service
public class RoleServiceImpl implements IRoleService {
	private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Resource
	private MenuDao menuDao;
	
	@Resource 
	private RoleDao dao;
	
	@Autowired
	private OfficeDao officeDao;
	
	
	public PageMsg<Role> getRoleList(RoleForm form) throws Exception{/////
		 List<Role> list = dao.getRoleList(form);
		 PageMsg<Role> pageObj = new PageMsg<Role>();
		 pageObj.setListResult(list);
		 pageObj.setTotalNum(dao.getRoleListTotal(form));
		 pageObj.setPageNum(form.getSize());
		 pageObj.setPage(form.getCurr());
		 pageObj.setTotalPage();
		 return pageObj;
	}
	
	@Override
	public List<Menu> getMenuList(DataAuthority dataAuthority) throws Exception {/////
		return dao.getMenuList(dataAuthority);
	}
	

	@Override
	public List<Office> getOfficeList(DataAuthority dataAuthority) throws Exception {/////
		return dao.getOfficeList(dataAuthority);
	}
	
	
	@Override
	public boolean doAddOrUpdate(Role role) throws Exception {////
		List<Role> list = dao.isRole(role);
		if (list.size() > 0) {
			return false;
		}
		
		int i = 0;
		//菜单
		String menus = role.getMenus();
		String[] menu = menus.split(",");
		ArrayList<String> menuList = new ArrayList<String>();
		for (String string : menu) {
			menuList.add(string);
		}
		role.setMenuList(menuList);
		
		//机构
		String offices = role.getOffices();
		String[] office = offices.split(",");
		ArrayList<Map<String, String>> officeList = new ArrayList<Map<String, String>>();
		for (String string : office) {
			String[] of = string.split("-");
			Map<String, String> map = new HashMap<String, String>();
			String officeId =  of[0];
			String level =  of[1];
			
			map.put("officeId",officeId );
			
			if("1".equals(level)){
				Office off = officeDao.getOffice(Integer.parseInt(offices));
				if(off!=null && off.getHasChild() > 0) {
					map.put("level", 1+"");  // 还有下級
				} else {
					map.put("level", 0+"");  // 还有下級
				}
			} else if("2".equals(level)) {
				map.put("level", 1+"");
			} else {
				map.put("level", 0+"");
			}
			officeList.add(map);
		}
		role.setOfficeList(officeList);
		
		
		if (role.getId() > 0) {//修改
			i = dao.updateRole(role);
			
			dao.deleteMenus(role);
			dao.addMenus(role);
			dao.deleteOffice(role);
			dao.addOffice(role);
		} else {//添加
			i = dao.addRole(role);
			
			dao.deleteMenus(role);
			dao.addMenus(role);
			dao.deleteOffice(role);
			dao.addOffice(role);
		}
		return true;
	}

	@Override
	public Role getRole(int id) throws Exception {//////
		return dao.getRole(id);
	}
	

	@Override
	public boolean delete(String roleId) throws Exception {///
		// TODO Auto-generated method stub
		int count = dao.selectRoleUser(roleId);
		if (count == 0) {
			int i = dao.delete(roleId);
			return i > 0;
		}
		return false;
	}
	
	
	/*
	*//**
	 * 获取子级菜单以及菜单和角色的关联关系
	 * @param roleId 角色编号
	 * @param myUserId 当前操作的用户
	 * @return
	 *//*
	public List<TreeNode> getMenuTreeNodes(int roleId, String myUserId) throws Exception {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<RoleMenu> roleMenus = menuDao.qryMenusByRoleId(roleId, myUserId);
		TreeNode treeNode = new TreeNode();
		treeNode.setId(0);
		treeNode.setChecked(true);
		treeNode.setName("菜单");
		treeNode.setIsParent(true);
		treeNode.setPId(0);
		treeNodes.add(treeNode);
		for (RoleMenu rolemenu : roleMenus) {
			treeNode = new TreeNode();
			treeNode.setId(rolemenu.getMenuId());
			treeNode.setName(rolemenu.getMenuName());
			treeNode.setIsParent(rolemenu.getIsparent());
			treeNode.setPId(rolemenu.getParentId());
			if (rolemenu.getRoleId() < 1) {
				treeNode.setChecked(false);
			} else {
				treeNode.setChecked(true);
			}
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

	*//**
	 * 设置角色的菜单
	 * @param roleId
	 * @param menuIds
	 * @throws Exception
	 *//*
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public void setRoleMenus(int roleId, String menuIds) throws Exception {
		menuDao.delAllRoleMenu(roleId);
		String[] ids = menuIds.split(",");
		for (String menuId : ids) {
			if (!StringUtil.isEmpty(menuId)) {
				int mId = Integer.parseInt(menuId);
				if (mId > 0) {
					menuDao.addRoleMenu(roleId, mId);
				}
			}
		}
	}*/
	@Override
	public List<Integer> queryRoleOfficeIds(Integer roleId) {
		// TODO Auto-generated method stub
		return dao.queryRoleOfficeIds(roleId);
	}



}
