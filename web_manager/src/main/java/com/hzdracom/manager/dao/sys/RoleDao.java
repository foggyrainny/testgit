package com.hzdracom.manager.dao.sys;

import java.util.List;

import com.hzdracom.manager.bean.DataAuthority;
import com.hzdracom.manager.bean.form.RoleForm;
import com.hzdracom.manager.bean.sys.Menu;
import com.hzdracom.manager.bean.sys.Office;
import com.hzdracom.manager.bean.sys.Role;

/**
 * @title: RoleDao2.java
 * @pacjage: com.hzdracom.manager.dao.sys
 * @description: TODO
 * @author: 高辉
 * @date: 2017年3月24日 下午3:13:40
 */
public interface RoleDao {
	
	public List<Role> getRoleList(RoleForm form) throws Exception;
	
	public int getRoleListTotal(RoleForm form) throws Exception;
	
	public List<Menu> getMenuList(DataAuthority dataAuthority) throws Exception;
	
	public List<Office> getOfficeList(DataAuthority dataAuthority) throws Exception;
	
	public int addRole(Role role) throws Exception;
	
	public int updateRole(Role role) throws Exception;
	
	public int deleteMenus(Role role) throws Exception;
	
	public int deleteOffice(Role role) throws Exception;
	
	public int addMenus(Role role) throws Exception;
	
	public int addOffice(Role role) throws Exception;
	
	public Role getRole(int id) throws Exception;
	
	List<Role> isRole(Role v);
	
	/** 查询数据权限 */
	public List<Integer>  queryRoleOfficeIds(Integer roleId);
	
	int delete(String roleId);
	int selectRoleUser(String roleId);
}
